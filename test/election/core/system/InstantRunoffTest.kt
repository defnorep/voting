package election.core.system

import election.core.Candidate
import election.core.Party
import election.core.vote.RankedCandidateVote
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InstantRunoffTest {
    @Test
    fun result_unopposed() {
        val sys = InstantRunoff()
        val candidate1 = Candidate("Molly", Party("Dog Party"))
        sys.castVote(RankedCandidateVote(setOf(candidate1)))
        sys.castVote(RankedCandidateVote(setOf(candidate1)))
        val result = sys.result()

        assertEquals(candidate1, result.candidate)
        assertEquals(2, result.votes)
        assertEquals(2, result.totalVotes)
    }

    @Test
    fun result_firstRoundMajority() {
        val sys = InstantRunoff()
        val candidate1 = Candidate("Molly", Party("Dog Party"))
        val candidate2 = Candidate("Bean", Party("Cat Party"))
        sys.castVote(RankedCandidateVote(setOf(candidate1, candidate2)))
        sys.castVote(RankedCandidateVote(setOf(candidate1, candidate2)))
        sys.castVote(RankedCandidateVote(setOf(candidate2, candidate1)))
        val result = sys.result()

        assertEquals(candidate1, result.candidate)
        assertEquals(2, result.votes)
        assertEquals(3, result.totalVotes)
    }

    @Test
    fun result_secondRoundMajority() {
        val sys = InstantRunoff()
        val candidate1 = Candidate("Molly", Party("Dog Party"))
        val candidate2 = Candidate("Bean", Party("Cat Party"))
        val candidate3 = Candidate("Hammie", Party("Hamster Party"))
        sys.castVote(RankedCandidateVote(setOf(candidate1, candidate2)))
        sys.castVote(RankedCandidateVote(setOf(candidate2, candidate1)))
        sys.castVote(RankedCandidateVote(setOf(candidate3, candidate2)))
        sys.castVote(RankedCandidateVote(setOf(candidate1, candidate2)))
        sys.castVote(RankedCandidateVote(setOf(candidate2, candidate1)))
        sys.castVote(RankedCandidateVote(setOf(candidate3, candidate2)))
        val result = sys.result()

        assertEquals(candidate2, result.candidate)
        assertEquals(4, result.votes)
        assertEquals(6, result.totalVotes)
    }
}
