package election.system

import election.winner.SingleWinner
import election.vote.Vote

interface Majority<V: Vote> : ElectoralSystem<SingleWinner, V> {}