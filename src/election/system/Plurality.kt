package election.system

import election.vote.Vote
import election.winner.Winner

interface Plurality<W : Winner, V : Vote> : ElectoralSystem<W, V> {}