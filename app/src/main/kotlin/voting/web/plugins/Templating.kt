package voting.voting.web.plugins

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.mustache.Mustache
import io.ktor.server.mustache.MustacheContent
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import voting.core.Candidate
import voting.core.District
import voting.core.Election
import voting.core.Party
import voting.core.system.FirstPastThePost
import voting.core.system.InstantRunoff
import voting.core.vote.RankedCandidateVote
import voting.core.vote.SingleCandidateVote
import voting.core.winner.SingleWinner

fun runFptp(): Pair<SingleWinner, District> {
    val molly = Candidate("Molly", Party("Dog Party"))
    val bean = Candidate("Bean", Party("Cat Party"))
    val district = District("Hexadecimal City", setOf(molly, bean))
    val system = FirstPastThePost()
    val election = Election(district, system)
    val candidates = setOf(molly, bean)
    election.castVote(SingleCandidateVote(candidates.first()))
    election.castVote(SingleCandidateVote(candidates.first()))
    election.castVote(SingleCandidateVote(candidates.first()))
    election.castVote(SingleCandidateVote(candidates.last()))
    val result = election.result()

    return Pair(result, district);
}

fun runInstantRunoff(): Pair<SingleWinner, District> {
    val willa = Candidate("Willa", Party("Dog Party"))
    val aristotle = Candidate("Aristotle", Party("Cat Party"))
    val district = District("Octal Town", setOf(willa, aristotle))
    val candidates = setOf(aristotle, willa)
    val system = InstantRunoff()
    val election = Election(district, system)
    election.castVote(RankedCandidateVote(setOf(candidates.first(), candidates.last())))
    election.castVote(RankedCandidateVote(setOf(candidates.first(), candidates.last())))
    election.castVote(RankedCandidateVote(setOf(candidates.first(), candidates.last())))
    election.castVote(RankedCandidateVote(setOf(candidates.first(), candidates.last())))
    election.castVote(RankedCandidateVote(setOf(candidates.last(), candidates.first())))
    election.castVote(RankedCandidateVote(setOf(candidates.last(), candidates.first())))
    election.castVote(RankedCandidateVote(setOf(candidates.last(), candidates.first())))
    val result = election.result()

    return Pair(result, district)
}

fun Application.configureTemplating() {
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }
    routing {
        get("/") {
            val fptp = runFptp();
            val ir = runInstantRunoff();

            call.respond(
                MustacheContent(
                    "index.html", mapOf(
                        "results" to listOf(
                            mapOf(
                                "system" to "First Past the Post",
                                "district" to fptp.second.name,
                                "candidate" to fptp.first.candidate.name,
                                "party" to fptp.first.candidate.party.name,
                                "votes" to fptp.first.votes,
                                "votePercent" to fptp.first.votePercent(),
                            ), mapOf(
                                "system" to "Instant Runoff",
                                "district" to ir.second.name,
                                "candidate" to ir.first.candidate.name,
                                "party" to ir.first.candidate.party.name,
                                "votes" to ir.first.votes,
                                "votePercent" to ir.first.votePercent(),
                            )
                        )
                    )
                )
            )
        }
    }
}
