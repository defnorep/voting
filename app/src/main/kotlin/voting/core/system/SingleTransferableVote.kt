package voting.core.system

import voting.core.vote.RankedVote
import voting.core.winner.MultiWinner

class SingleTransferableVote : Proportional<MultiWinner, RankedVote> {
    override val votes: MutableList<RankedVote> = mutableListOf()

    override fun result(): MultiWinner {
        return MultiWinner()
    }
}
