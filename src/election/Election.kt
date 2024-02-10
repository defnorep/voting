package election

import election.system.ElectoralSystem
import election.vote.Vote
import election.winner.Winner

class Election<W : Winner, V : Vote, E : ElectoralSystem<W, V>>(
    val district: District,
    private val system: E
) : ElectoralSystem<W, V> by system
