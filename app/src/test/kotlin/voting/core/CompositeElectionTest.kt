package voting.core

import voting.core.system.FirstPastThePost
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CompositeElectionTest {
    @Test
    fun getElection() {
        val party = Party("Modernity")
        val candidate = Candidate("Matthew Evans", party)
        val district = District("Hexadecimal City", setOf(candidate))
        val system = FirstPastThePost()
        val expectedElection = Election(district, system)
        val compositeElection = CompositeElection(setOf(expectedElection))
        val actualElection = compositeElection.getElection(district)

        assertEquals(expectedElection, actualElection)
    }

    @Test
    fun getElections() {
        val party = Party("Modernity")
        val candidate = Candidate("Matthew Evans", party)
        val district = District("Hexadecimal City", setOf(candidate))
        val system = FirstPastThePost()
        val expectedElection = Election(district, system)
        val compositeElection = CompositeElection(setOf(expectedElection))
        val actualElections = compositeElection.getElections()

        assertEquals(listOf(expectedElection), actualElections)
    }
}
