import election.cli.formatter.SingleWinnerDistrictResultFormatter
import election.core.Candidate
import election.core.CompositeElection
import election.core.District
import election.core.Election
import election.core.Party
import election.core.system.FirstPastThePost
import election.core.vote.SingleCandidateVote

fun main() {
    val party = Party("Modernity")
    val candidate = Candidate("Matthew Evans", party)
    val district = District("Hexadecimal City", setOf(candidate))
    val system = FirstPastThePost()
    val election = Election(district, system)
    val compositeElection = CompositeElection(setOf(election))
    compositeElection.getElection(district)?.castVote(SingleCandidateVote(candidate))
    compositeElection.getElection(district)?.castVote(SingleCandidateVote(candidate))
    compositeElection.getElection(district)?.castVote(SingleCandidateVote(candidate))
    compositeElection.getElections().forEach { println(SingleWinnerDistrictResultFormatter(it.district, it.result()).format()) }
}
