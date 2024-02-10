package election.system

import election.vote.SingleCandidateVote
import election.winner.SingleWinner

class FirstPastThePost : Plurality<SingleWinner, SingleCandidateVote> {
    override val votes: MutableList<SingleCandidateVote> = mutableListOf()
    override fun result(): SingleWinner {
        return SingleWinner(this.votes.first().candidate)
    }
}