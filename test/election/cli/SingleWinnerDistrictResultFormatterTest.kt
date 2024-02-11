package election.cli

import election.Candidate
import election.District
import election.Party
import election.cli.formatter.SingleWinnerDistrictResultFormatter
import election.winner.SingleWinner
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SingleWinnerDistrictResultFormatterTest {
    @Test
    fun format() {
        val party = Party("Modernity")
        val candidate = Candidate("Matthew Evans", party)
        val district = District("Hexadecimal City", setOf(candidate))
        val winner = SingleWinner(candidate)
        val formatter = SingleWinnerDistrictResultFormatter(district, winner)

        assertEquals("Hexadecimal City: Matthew Evans", formatter.format())
    }
}
