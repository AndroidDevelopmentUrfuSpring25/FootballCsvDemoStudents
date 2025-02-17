package resolver

import model.Player
import model.Team

class Resolver (_players: List<Player>){
    val players: List<Player> = _players

    fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isEmpty() }
    }

    fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position == "защитник" }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals }?: throw Exception("not found")
    }

    fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position?: throw Exception("not found")
    }

    fun getTheRudestTeam(): Team {
        val teamWithMaxAvgRedCards = players
            .groupBy { it.team.name }
            .mapValues { entry -> entry.value.map { it.redCards }.average()}
            .maxByOrNull { it.value }

        return teamWithMaxAvgRedCards?.let {
            players.first { player -> player.team.name == it.key }.team
        } ?: throw IllegalArgumentException("not found")

    }
}