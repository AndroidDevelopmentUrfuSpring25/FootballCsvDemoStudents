package resolver

import model.Player
import model.PlayerPosition
import model.Team

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int = players.count { it.agency.isBlank() }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val defenders = players
            .filter { it.position == PlayerPosition.DEFENDER }
            .maxByOrNull { it.goals }
            ?: throw Exception("No defenders found")

        return defenders.let { it.name to it.goals }
    }


    override fun getTheExpensiveGermanPlayerPosition(): String = players
        .filter { it.nationality == "Germany" }
        .maxBy { it.transferCost }.position.russianName


    override fun getTheRudestTeam(): Team = players
        .groupBy { it.team }
        .mapValues { team -> team.value.map { it.redCards }.average() }
        .maxBy { it.value }
        .key
}