package election.core.system

import election.core.Candidate
import election.core.vote.Vote
import election.core.winner.SingleWinner

interface Majority<V : Vote> : ElectoralSystem<SingleWinner, V> {
    fun hasMajority(count: Map<Candidate, Int>): Boolean
}
