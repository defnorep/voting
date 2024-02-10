package election.system

import election.vote.RankedVote
import election.winner.MultiWinner

class SingleTransferableVote : Proportional<MultiWinner, RankedVote> {
    override val votes: MutableList<RankedVote> = mutableListOf()

    override fun result(): MultiWinner {
        return MultiWinner()
    }
}