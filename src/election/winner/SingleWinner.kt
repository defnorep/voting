package election.winner

import election.Candidate

class SingleWinner(val candidate: Candidate, val votes: Int, val totalVotes: Int) : Winner
