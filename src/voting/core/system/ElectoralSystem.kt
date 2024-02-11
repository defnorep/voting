package voting.core.system

import voting.core.vote.Vote
import voting.core.winner.Winner

interface ElectoralSystem<W : Winner, V : Vote> {
    val votes: MutableList<V>

    fun result(): W

    fun castVote(vote: V) {
        this.votes.addFirst(vote)
    }
}
