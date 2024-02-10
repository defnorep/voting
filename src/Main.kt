import election.*
import election.system.FirstPastThePost
import election.vote.SingleCandidateVote

fun main() {
    val party = Party("Modernity")
    val candidate = Candidate("Matthew Evans", party)
    val district = District("Hexadecimal City", setOf(candidate))
    val system = FirstPastThePost()
    val election = Election(district, system)
    val compositeElection = CompositeElection(listOf(election))
    compositeElection.getElection(district.name)?.castVote(SingleCandidateVote(candidate))
    val result = compositeElection.result()
    println(result)
}