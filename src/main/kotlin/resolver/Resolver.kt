package resolver

import model.Player
import model.Position
import model.Team

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int = players.count { p -> p.agency.isEmpty() }

    override fun getBestScorerDefender(): Pair<String, Int> =
        players.filter {it.position == Position.DEFENDER }
        .maxByOrNull { it.goals }
        ?.let { it.name to it.goals }
        ?: throw Exception("Defenders list is empty")

    override fun getTheExpensiveGermanPlayerPosition(): String =
        players.filter { it.nationality == "Germany" }
        .maxByOrNull { it.transferCost }?.position?.translationRu
        ?: throw Exception("Germans list is empty")

    override fun getTheRudestTeam(): Team =
        players.groupBy { it.team }
        .maxByOrNull { it.value.map { it.redCards }.average() }?.key
        ?: throw Exception("Teams grouping is empty")
}