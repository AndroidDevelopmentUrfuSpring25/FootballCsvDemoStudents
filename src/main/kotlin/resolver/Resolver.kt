package resolver

import model.Player
import model.PlayerPosition
import model.Team

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == "" }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestScorer = players
            .filter { it.position == "DEFENDER" }
            .maxBy { it.goals }

        return bestScorer.name to bestScorer.goals
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensiveGerman = players
            .filter { it.nationality == "Germany" }
            .maxBy { it.transferCost }

        return PlayerPosition.valueOf(expensiveGerman.position).russianName
    }

    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team.name }
            .mapValues { team -> team.value.map { it.redCards }.average() }
            .maxBy { it.value }.let {
                players.first { player -> player.team.name == it.key }.team
            }
    }

}