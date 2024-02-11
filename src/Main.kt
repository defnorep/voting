import voting.cli.formatter.SingleWinnerDistrictResultFormatter
import voting.core.Candidate
import voting.core.District
import voting.core.Election
import voting.core.Party
import voting.core.system.FirstPastThePost
import voting.core.system.InstantRunoff
import voting.core.vote.RankedCandidateVote
import voting.core.vote.SingleCandidateVote

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
