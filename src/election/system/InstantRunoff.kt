package election.system

import election.Candidate
import election.Party
import election.vote.RankedVote
import election.winner.SingleWinner

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
