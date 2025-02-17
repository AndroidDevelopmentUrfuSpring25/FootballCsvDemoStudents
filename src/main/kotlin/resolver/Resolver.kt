package resolver

import model.Player
import model.Position
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == "" }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goalsCount }
            ?.let { it.name to it.goalsCount }
            ?: throw Exception("Defenders not founded")

    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }?.position?.russianName
            ?: throw Exception("Germany player no founded")
    }

    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .maxByOrNull { it.value.map { it.redCardsCount }.average() }
            ?.key
            ?: throw Exception("Empty players list")
    }
}