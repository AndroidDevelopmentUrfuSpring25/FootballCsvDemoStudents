package resolver
import model.Team
import model.Position
import model.Player


class Resolver(private val players: List<Player>) : IResolver{

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isEmpty()}
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { it.name to (it.goals) }
            ?: throw Exception("Нет защитников")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
        .filter { it.nationality == "Germany" }
        .maxBy { it.transfer}.position.russianNames
    }

    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .maxByOrNull { (_, teamPlayers) -> teamPlayers.map { it.red }.average() }
            ?.key
            ?: throw  Exception("Error")
    }

    override fun topTen(): List<Pair<String, Int>> {

        return players
            .groupBy { it.team }
            .map { (team, teamPlayers) ->
                team.name to teamPlayers.sumOf { it.transfer }
            }
            .sortedByDescending { it.second }
            .take(10)

    }
}