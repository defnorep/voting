import election.cli.formatter.SingleWinnerDistrictResultFormatter
import election.core.Candidate
import election.core.District
import election.core.Election
import election.core.Party
import election.core.system.FirstPastThePost
import election.core.system.InstantRunoff
import election.core.vote.RankedCandidateVote
import election.core.vote.SingleCandidateVote

fun main() {
    val party = Party("Modernity")
    val candidate = Candidate("Matthew Evans", party)
    val district = District("Hexadecimal City", setOf(candidate))

    fptp(candidate, district)
    println("\r\n")
    runoff(candidate, district)
}

fun fptp(
    candidate: Candidate,
    district: District,
) {
    val system = FirstPastThePost()
    val election = Election(district, system)
    election.castVote(SingleCandidateVote(candidate))
    println("First Past The Post Results:")
    println(SingleWinnerDistrictResultFormatter(election.district, election.result()).format())
}

fun runoff(
    candidate: Candidate,
    district: District,
) {
    val system = InstantRunoff()
    val election = Election(district, system)
    election.castVote(RankedCandidateVote(setOf(candidate)))
    println("Instant Runoff Results:")
    println(SingleWinnerDistrictResultFormatter(election.district, election.result()).format())
}
