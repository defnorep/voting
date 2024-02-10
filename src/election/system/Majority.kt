package election.system

import election.vote.Vote
import election.winner.SingleWinner

interface Majority<V : Vote> : ElectoralSystem<SingleWinner, V>
