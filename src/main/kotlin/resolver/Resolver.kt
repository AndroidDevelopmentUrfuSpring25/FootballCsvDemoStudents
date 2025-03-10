package resolver

import model.Player
import model.PlayerPosition
import model.Team

class Resolver(private val players: List<Player>) {

    fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isEmpty() }
    }

    fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position == PlayerPosition.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals } ?: throw Exception("not found")
    }

    fun getTheExpensiveGermanPlayerPosition(): PlayerPosition {
        return players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position ?: throw Exception("not found")
    }

    fun getTheRudestTeam(): Team {
        val teamWithMaxAvgRedCards = players
            .groupBy { it.team.name }
            .mapValues { entry -> entry.value.map { it.redCards }.average() }
            .maxByOrNull { it.value }

        return teamWithMaxAvgRedCards?.let {
            players.first { player -> player.team.name == it.key }.team
        } ?: throw Exception("not found")

    }
}