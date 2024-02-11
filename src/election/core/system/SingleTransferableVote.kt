package election.core.system

import election.core.vote.RankedVote
import election.core.winner.MultiWinner

class SingleTransferableVote : Proportional<MultiWinner, RankedVote> {
    override val votes: MutableList<RankedVote> = mutableListOf()

    override fun result(): MultiWinner {
        return MultiWinner()
    }
}
