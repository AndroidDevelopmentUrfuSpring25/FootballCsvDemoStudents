package resolver

import model.Player
import model.Position
import model.Team

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { Pair(it.name, it.goals) }
            ?: throw NoSuchElementException("The defender with the most goals has not been found.")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position?.russianName
            ?: throw NoSuchElementException("The position name of the most expensive German player was not found")
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
            .maxByOrNull { it.value.map { player -> player.redCards }.average() }
            ?.key
            ?: throw NoSuchElementException("The team with the highest average number of red cards per player was not found.")
    }

    fun getForwards(): List<Player> {
        return players.filter { it.position == Position.FORWARD }
    }
}