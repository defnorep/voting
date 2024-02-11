package voting.core.system

import voting.core.Candidate
import voting.core.vote.Vote
import voting.core.winner.SingleWinner

interface Majority<V : Vote> : ElectoralSystem<SingleWinner, V> {
    fun hasMajority(count: Map<Candidate, Int>): Boolean
}
