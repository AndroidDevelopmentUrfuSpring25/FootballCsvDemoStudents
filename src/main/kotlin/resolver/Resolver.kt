package resolver

import enums.Position
import model.Player
import model.Team
import kotlin.Exception

class Resolver(private val playerList: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return playerList.count { it.agency.isEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return playerList
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals }?: throw Exception()
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return playerList.filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }?.position?.russianName.orEmpty()
    }

    override fun getTheRudestTeam(): Team {
        return playerList
            .groupBy { it.team }
            .mapValues { (_, players) ->
                players.map { it.redCards }.average()
            }
            .maxBy { it.value }.key
    }
}