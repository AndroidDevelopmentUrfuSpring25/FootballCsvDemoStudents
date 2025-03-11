package resolver

import model.Player
import model.Team
import model.PlayerPosition

class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position == PlayerPosition.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals }
            ?: ("Нет данных" to 0)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val germanPlayer = players.filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferValue }

        return when (germanPlayer?.position?.name ?: "UNKNOWN") {
            "FORWARD" -> "Нападающий"
            "MIDFIELDER" -> "Полузащитник"
            "DEFENDER" -> "Защитник"
            "GOALKEEPER" -> "Вратарь"
            else -> "Неизвестная позиция"
        }
    }

    override fun getTheRudestTeam(): String {
        return players.groupBy { it.team }
            .mapValues { (_, teamPlayers) ->
                teamPlayers.sumOf { it.redCards } / teamPlayers.size.toDouble()
            }
            .maxByOrNull { it.value }
            ?.key?.name ?: "Нет данных"
    }

    override fun calculatePositionDistribution(): Map<String, Double> {
        val totalPlayers = players.size
        return players.groupingBy { it.position.toString() }
            .eachCount()
            .mapValues { (_, count) -> (count.toDouble() / totalPlayers) * 100 }
    }
}
