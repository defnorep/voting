package election.cli.formatter

import election.core.District
import election.core.winner.SingleWinner

class SingleWinnerDistrictResultFormatter(private val district: District, private val winner: SingleWinner) :
    Formatter {
    override fun format(): String {
        return String.format(
            "%s won by %s (%s) with %s votes (%s%% of vote).",
            this.district.name,
            this.winner.candidate.name,
            this.winner.candidate.party.name,
            this.winner.votes,
            (this.winner.votes / this.winner.totalVotes) * 100,
        )
    }
}
