package election.system

import election.vote.Vote
import election.winner.Winner

interface Proportional<W : Winner, V : Vote> : ElectoralSystem<W, V>
