package election.system

import election.vote.Vote
import election.winner.Winner

interface ElectoralSystem<W: Winner, V: Vote> {
    val votes: MutableList<V>

    fun result(): W
    fun castVote(vote: V) {
        this.votes.addFirst(vote)
    }
}