package election.core.system

import election.core.vote.Vote
import election.core.winner.Winner

interface ElectoralSystem<W : Winner, V : Vote> {
    val votes: MutableList<V>

    fun result(): W

    fun castVote(vote: V) {
        this.votes.addFirst(vote)
    }
}
