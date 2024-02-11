package election.core.system

import election.core.vote.Vote
import election.core.winner.Winner

interface Plurality<W : Winner, V : Vote> : ElectoralSystem<W, V>
