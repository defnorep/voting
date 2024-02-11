package election.core.system

import election.core.vote.Vote
import election.core.winner.Winner

interface Proportional<W : Winner, V : Vote> : ElectoralSystem<W, V>
