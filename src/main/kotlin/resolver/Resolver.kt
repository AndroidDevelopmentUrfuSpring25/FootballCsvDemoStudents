package resolver

import model.Player
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    val positionToRussian: Map<String, String> = mapOf(
        "DEFENDER" to "Защитник",
        "GOALKEEPER" to "Вратарь",
        "MIDFIELD" to "Полузащитник",
        "FORWARD" to "Нападающий"
    )

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isEmpty() }


    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position.equals("Defender", ignoreCase = true) }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals } ?: Pair("No defenders", 0)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String? {
        return positionToRussian[players
            .filter { it.nationality.equals("Germany", ignoreCase = true) }
            .maxByOrNull { it.transferPrice }
            ?.position ?: "No German players"]
    }

    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .mapValues { entry ->
                val totalRedCards = entry.value.sumOf { it.redCardCount }
                totalRedCards.toDouble() / entry.value.size
            }
            .maxByOrNull { it.value }
            ?.key ?: Team("No team", "Unknown")
    }

    fun getTheMostStransferCostTeam(): List<Pair<Team, Int>> {
        return players
            .groupBy { it.team }
            .mapValues { entry ->
                entry.value.sumOf { it.transferPrice }
            }
            .toList()
            .sortedByDescending { it.second }
            .take(10)
            .map { Pair(it.first, it.second) }
    }


}