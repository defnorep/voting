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

fun Application.configureTemplating() {
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }
    routing {
        get("/") {
            call.respond(MustacheContent("index.html", mapOf("name" to "Matthew")))
        }
        get("/templates/fptp") {
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
            val votePercent = (result.votes.toFloat() / result.totalVotes.toFloat()) * 100

            call.respond(
                MustacheContent(
                    "fptp.html",
                    mapOf(
                        "district" to district.name,
                        "candidate" to result.candidate.name,
                        "party" to result.candidate.party.name,
                        "votes" to result.votes,
                        "votePercent" to votePercent,
                    ),
                ),
            )
        }
        get("/templates/runoff") {
            val willa = Candidate("Willa", Party("Dog Party"))
            val merlin = Candidate("Aristotle", Party("Cat Party"))
            val district = District("Octal Town", setOf(willa, merlin))
            val candidates = setOf(merlin, willa)
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
            val votePercent = String.format("%.1f", (result.votes.toFloat() / result.totalVotes.toFloat()) * 100)

            call.respond(
                MustacheContent(
                    "runoff.html",
                    mapOf(
                        "district" to district.name,
                        "candidate" to result.candidate.name,
                        "party" to result.candidate.party.name,
                        "votes" to result.votes,
                        "votePercent" to votePercent,
                    ),
                ),
            )
        }
    }
}
