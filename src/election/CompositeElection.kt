package election

import election.system.ElectoralSystem
import election.vote.Vote
import election.winner.Winner

class CompositeElection<W: Winner, V: Vote, E: ElectoralSystem<W, V>>(elections: List<Election<W, V, E>>) {
    private val electionsByDistrict = elections.associateBy { it.district.name }

    fun getElection(districtName: String): Election<W, V, E>? {
        return this.electionsByDistrict.get(districtName)
    }

    fun result(): List<W> {
        return this.electionsByDistrict.map { it.value.result() }
    }
}