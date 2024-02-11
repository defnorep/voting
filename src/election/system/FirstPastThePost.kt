package election.system

import election.vote.SingleCandidateVote
import election.winner.SingleWinner

class FirstPastThePost : Plurality<SingleWinner, SingleCandidateVote> {
    override val votes: MutableList<SingleCandidateVote> = mutableListOf()

    override fun result(): SingleWinner {
        val winner = this.votes.groupBy { it.candidate.party }.maxByOrNull { it.value.size }
        if (winner == null) {
            throw Exception("Zero votes cast.")
        }

        return SingleWinner(winner.value.first().candidate, winner.value.size, this.votes.size)
    }
}
