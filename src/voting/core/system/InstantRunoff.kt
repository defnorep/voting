package voting.core.system

import voting.core.Candidate
import voting.core.vote.RankedCandidateVote
import voting.core.winner.SingleWinner

/**
 * Assumptions:
 * - All ballots must rank all candidates; no gaps are allowed. Gaps == crash. These rules change by election system.
 */
class InstantRunoff : Majority<RankedCandidateVote> {
    override val votes: MutableList<RankedCandidateVote> = mutableListOf()

    override fun result(): SingleWinner {
        val (winner, votes) = this.process(this.votes)

        return SingleWinner(
            winner,
            votes,
            this.votes.size,
        )
    }

    override fun hasMajority(count: Map<Candidate, Int>): Boolean {
        val (_, votes) = this.highestFirstPreferenceVotes(count)

        val totalVotes = count.entries.sumOf { it.value }

        return (votes.toFloat() / totalVotes.toFloat()) > 0.5
    }

    private fun countFirstPreferenceVotes(votes: MutableList<RankedCandidateVote>): Map<Candidate, Int> {
        return votes.groupingBy { it.candidates.first() }.eachCount()
    }

    private fun highestFirstPreferenceVotes(count: Map<Candidate, Int>): Pair<Candidate, Int> {
        val highestFirstPreferenceVotes = count.maxByOrNull { it.value }

        if (highestFirstPreferenceVotes == null) {
            throw Exception("Zero votes cast.")
        }

        return Pair(highestFirstPreferenceVotes.key, highestFirstPreferenceVotes.value)
    }

    private fun lowestFirstPreferenceVotes(count: Map<Candidate, Int>): Pair<Candidate, Int> {
        val lowestFirstPreferenceVotes = count.minByOrNull { it.value }

        if (lowestFirstPreferenceVotes == null) {
            throw Exception("Zero votes cast.")
        }

        return Pair(lowestFirstPreferenceVotes.key, lowestFirstPreferenceVotes.value)
    }

    private fun eliminateCandidateFromVotes(
        votes: MutableList<RankedCandidateVote>,
        candidate: Candidate,
    ): MutableList<RankedCandidateVote> {
        return votes.map { RankedCandidateVote(it.candidates.filter { c -> c != candidate }.toSet()) }.toMutableList()
    }

    private tailrec fun process(votes: MutableList<RankedCandidateVote>): Pair<Candidate, Int> {
        // If there are only votes for one candidate, then they win.
        val count = this.countFirstPreferenceVotes(votes)

        if (this.hasMajority(count)) {
            return this.highestFirstPreferenceVotes(count)
        }

        // Eliminate the candidate appearing as the first preference on the fewest ballots
        val (lowestFirstPreferenceVote) = this.lowestFirstPreferenceVotes(count)
        val eliminatedVotes = this.eliminateCandidateFromVotes(votes, lowestFirstPreferenceVote)

        // Run again with voters' next choices as the losing candidate has been removed
        return this.process(eliminatedVotes.toMutableList())
    }
}
