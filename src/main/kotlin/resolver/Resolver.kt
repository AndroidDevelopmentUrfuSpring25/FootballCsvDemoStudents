package resolver

import model.Player
import model.Team

private val positionMapping = mapOf(
    "GOALKEEPER" to "вратарь",
    "DEFENDER" to "защитник",
    "MIDFIELD" to "полузащитник",
    "FORWARD" to "нападающий"
)

class Resolver(private val players: List<Player>): IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val defender = players.filter { it.position.uppercase() == "DEFENDER" }
            .maxByOrNull { it.goals }
        return if (defender != null) {
            defender.name to defender.goals
        } else {
            "" to 0
        }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val germanPlayer = players.filter { it.nationality.equals("Germany", ignoreCase = true) }
            .maxByOrNull { it.transferCost }
        val pos = germanPlayer?.position?.uppercase() ?: ""
        return positionMapping[pos] ?: pos
    }

    override fun getTheRudestTeam(): Team {
        val teamAverages = players.groupBy { it.team to it.city }
            .mapValues { entry ->
                entry.value.map { it.redCards }.average()
            }
        val maxEntry = teamAverages.maxByOrNull { it.value }
        return if (maxEntry != null) {
            Team(name = maxEntry.key.first, city = maxEntry.key.second)
        } else {
            Team(name = "", city = "")
        }
    }
}