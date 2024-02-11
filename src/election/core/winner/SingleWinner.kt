package election.core.winner

import election.core.Candidate

class SingleWinner(val candidate: Candidate, val votes: Int, val totalVotes: Int) : Winner
