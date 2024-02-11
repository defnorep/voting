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
    val molly = Candidate("Molly", Party("Dog Party"))
    val bean = Candidate("Bean", Party("Cat Party"))
    val district = District("Hexadecimal City", setOf(molly, bean))

    fptp(setOf(molly, bean), district)
    runoff(setOf(molly, bean), district)
}

fun fptp(
    candidates: Set<Candidate>,
    district: District,
) {
    val system = FirstPastThePost()
    val election = Election(district, system)
    election.castVote(SingleCandidateVote(candidates.first()))
    election.castVote(SingleCandidateVote(candidates.first()))
    election.castVote(SingleCandidateVote(candidates.first()))
    election.castVote(SingleCandidateVote(candidates.last()))
    println("First Past The Post Results:")
    println(SingleWinnerDistrictResultFormatter(election.district, election.result()).format())
    println("\n")
}

fun runoff(
    candidates: Set<Candidate>,
    district: District,
) {
    val system = InstantRunoff()
    val election = Election(district, system)
    election.castVote(RankedCandidateVote(setOf(candidates.first(), candidates.last())))
    election.castVote(RankedCandidateVote(setOf(candidates.first(), candidates.last())))
    election.castVote(RankedCandidateVote(setOf(candidates.first(), candidates.last())))
    election.castVote(RankedCandidateVote(setOf(candidates.last(), candidates.first())))
    println("Instant Runoff Results:")
    println(SingleWinnerDistrictResultFormatter(election.district, election.result()).format())
    println("\n")
}
