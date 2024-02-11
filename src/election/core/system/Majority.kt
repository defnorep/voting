package election.core.system

import election.core.vote.Vote
import election.core.winner.SingleWinner

interface Majority<V : Vote> : ElectoralSystem<SingleWinner, V>
