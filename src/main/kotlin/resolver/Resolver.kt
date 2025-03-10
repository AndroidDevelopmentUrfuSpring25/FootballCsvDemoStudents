package resolver

import model.Player
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == null }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position.name == "DEFENDER" }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals }
            ?: ("Unknown Player" to 0)
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

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
            .mapValues { (_, teamPlayers) -> teamPlayers.map { it.redCards }.average() }
            .maxByOrNull { it.value }
            ?.key ?: Team("Unknown Team", "Unknown City")
    }

    override fun top10TeamsByTransferValue(): List<Pair<String, Double>> {
        return players.groupBy { it.team }
            .mapValues { (_, teamPlayers) -> teamPlayers.sumOf { it.transferValue } }
            .toList()
            .sortedByDescending { it.second }
            .take(10)
            .map { it.first.name to it.second }
    }
}
