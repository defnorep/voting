package election.cli.formatter

import election.District
import election.winner.SingleWinner

class SingleWinnerDistrictResultFormatter(private val district: District, private val winner: SingleWinner) :
    Formatter {
    override fun format(): String {
        return String.format("%s won by %s", this.district.name, this.winner.candidate.name)
    }
}
