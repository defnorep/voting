package election

import election.system.ElectoralSystem
import election.vote.Vote
import election.winner.Winner

class CompositeElection<W : Winner, V : Vote, E : ElectoralSystem<W, V>>(
    elections: List<Election<W, V, E>>
) {
    private val electionsByDistrict = elections.associateBy { it.district }

    fun getElection(district: District): Election<W, V, E>? {
        return this.electionsByDistrict[district]
    }

    fun result(): List<Pair<District, W>> {
        return this.electionsByDistrict.map { Pair(it.key, it.value.result()) }
    }
}
