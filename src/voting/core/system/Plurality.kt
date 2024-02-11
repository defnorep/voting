package voting.core.system

import voting.core.vote.Vote
import voting.core.winner.Winner

interface Plurality<W : Winner, V : Vote> : ElectoralSystem<W, V>
