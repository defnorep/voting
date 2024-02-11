package election.core.system

import election.core.Candidate
import election.core.Party
import election.core.vote.RankedVote
import election.core.winner.SingleWinner

class InstantRunoff : Majority<RankedVote> {
    override val votes: MutableList<RankedVote> = mutableListOf()

    override fun result(): SingleWinner {
        return SingleWinner(
            Candidate("Not Implemented", Party("Not Implemented")),
            0,
            0,
        )
    }
}
