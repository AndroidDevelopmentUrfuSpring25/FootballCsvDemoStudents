package resolver

import model.Player
import model.Position
import model.Team

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int = players.count { it.agency.isEmpty() }

    override fun getBestScorerDefender(): Pair<String, Int> =
        players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goalsCount }
            ?.let { it.name to it.goalsCount }
            ?: throw Exception("no defenders")

    override fun getTheExpensiveGermanPlayerPosition(): String =
        players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position
            ?.russianName
            ?: throw Exception("no germans")

    override fun getTheRudestTeam(): Team =
        players
            .groupBy { it.team }
            .maxByOrNull { it.value.map { p -> p.redCardsCount }.average() }
            ?.key
            ?: throw Exception("no teams")
}