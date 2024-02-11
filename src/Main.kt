import election.Candidate
import election.CompositeElection
import election.District
import election.Election
import election.Party
import election.system.FirstPastThePost
import election.vote.SingleCandidateVote

fun main() {
    val party = Party("Modernity")
    val candidate = Candidate("Matthew Evans", party)
    val district = District("Hexadecimal City", setOf(candidate))
    val system = FirstPastThePost()
    val election = Election(district, system)
    val compositeElection = CompositeElection(listOf(election))
    compositeElection.getElection(district)?.castVote(SingleCandidateVote(candidate))
    compositeElection.getElection(district)?.castVote(SingleCandidateVote(candidate))
    compositeElection.getElection(district)?.castVote(SingleCandidateVote(candidate))
    compositeElection.result().forEach { println("${it.first.name}: ${it.second.candidate.name}") }
}
