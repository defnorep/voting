package voting.core.winner

import voting.core.Candidate

class SingleWinner(val candidate: Candidate, val votes: Int, val totalVotes: Int) : Winner
