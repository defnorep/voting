package election.cli.formatter

import election.Candidate
import election.District
import election.Party
import election.winner.SingleWinner
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SingleWinnerDistrictResultFormatterTest {
    @Test
    fun format() {
        val party = Party("Modernity")
        val candidate = Candidate("Matthew Evans", party)
        val district = District("Hexadecimal City", setOf(candidate))
        val winner = SingleWinner(candidate, 10, 10)
        val formatter = SingleWinnerDistrictResultFormatter(district, winner)

        assertEquals("Hexadecimal City won by Matthew Evans with 10 votes (100% of vote).", formatter.format())
    }
}
