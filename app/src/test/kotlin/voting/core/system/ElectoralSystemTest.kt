package voting.core.system

import voting.core.vote.SingleVote
import voting.core.winner.Winner
import kotlin.test.assertIs

class TestWinner : Winner

class TestVote : SingleVote

class TestSystem : ElectoralSystem<TestWinner, TestVote> {
    override val votes: MutableList<TestVote> = mutableListOf(TestVote())

    override fun result(): TestWinner {
        return TestWinner()
    }
}

class ElectoralSystemTest {
    @org.junit.jupiter.api.Test
    fun castvote() {
        val system = TestSystem()

        system.castVote(TestVote())

        assertIs<TestWinner>(system.result())
    }
}
