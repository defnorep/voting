package voting.core

import voting.core.system.ElectoralSystem
import voting.core.vote.Vote
import voting.core.winner.Winner

class CompositeElection<W : Winner, V : Vote, E : ElectoralSystem<W, V>>(
    elections: Set<Election<W, V, E>>,
) {
    private val electionsByDistrict = elections.associateBy { it.district }

    fun getElection(district: District): Election<W, V, E>? {
        return this.electionsByDistrict[district]
    }

    fun getElections(): List<Election<W, V, E>> {
        return this.electionsByDistrict.values.toList()
    }
}
