package voting.core.winner

import voting.core.Candidate
import voting.core.District

class SingleWinner(val candidate: Candidate, val votes: Int, val totalVotes: Int) : Winner {
    fun votePercent(): Float {
        return (this.votes.toFloat() / this.totalVotes.toFloat()) * 100;
    }
}
