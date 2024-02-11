package voting.core.system

import voting.core.vote.Vote
import voting.core.winner.Winner

interface Proportional<W : Winner, V : Vote> : ElectoralSystem<W, V>
