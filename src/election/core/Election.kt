package election.core

import election.core.system.ElectoralSystem
import election.core.vote.Vote
import election.core.winner.Winner

class Election<W : Winner, V : Vote, E : ElectoralSystem<W, V>>(
    val district: District,
    private val system: E,
) : ElectoralSystem<W, V> by system