package voting.core

import voting.core.system.ElectoralSystem
import voting.core.vote.Vote
import voting.core.winner.Winner

class Election<W : Winner, V : Vote, E : ElectoralSystem<W, V>>(
    val district: District,
    private val system: E,
) : ElectoralSystem<W, V> by system
