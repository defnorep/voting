package voting.core.system

import voting.core.Candidate
import voting.core.District
import voting.core.Election
import voting.core.Party
import voting.core.vote.SingleCandidateVote
import voting.core.winner.SingleWinner
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFailsWith

class FirstPastThePostTest {
    private fun createBaseElection(): Triple<
        Election<SingleWinner, SingleCandidateVote, FirstPastThePost>,
        Candidate,
        Candidate,
    > {
        val dogsParty = Party("Dogs")
        val catsParty = Party("Cats")
        val molly = Candidate("Molly", dogsParty)
        val bean = Candidate("Bean", catsParty)
        val district = District("Hexadecimal City", setOf(molly))

        val system = FirstPastThePost()
        val election = Election(district, system)

        return Triple(election, molly, bean)
    }

    @org.junit.jupiter.api.Test
    fun when_zerovotes_throwsException() {
        val (election) = this.createBaseElection()

        assertFailsWith<Exception> { election.result() }
    }

    @org.junit.jupiter.api.Test
    fun when_uniformvote_returnsWinner() {
        val (election, molly) = this.createBaseElection()

        election.castVote(SingleCandidateVote(molly))
        election.castVote(SingleCandidateVote(molly))
        election.castVote(SingleCandidateVote(molly))

        val result = election.result()

        assertEquals(molly, result.candidate)
    }

    @org.junit.jupiter.api.Test
    fun when_varyingvotes_returnsWinner() {
        val (election, molly, bean) = this.createBaseElection()

        election.castVote(SingleCandidateVote(molly))
        election.castVote(SingleCandidateVote(molly))
        election.castVote(SingleCandidateVote(bean))

        val result = election.result()

        assertEquals(molly, result.candidate)
    }
}
