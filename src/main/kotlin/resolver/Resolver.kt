package resolver
import model.Team

private val translate = mapOf(
    "FORWARD" to "Нападающий"
)

class Resolver(private val teams: List<Team>) : IResolver{

    override fun getCountWithoutAgency(): Int {
        return  teams
            .flatMap { it.players }
            .count {it.agency == ""}
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return teams
            .flatMap { it.players }
            .filter { it.position == "DEFENDER" }
            .maxBy { it.goals.toInt() }
            .let { it.name to (it.goals.toInt() ) }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return teams
            .flatMap { it.players }
            .filter { it.nationality == "Germany" }
            .maxBy { it.transfer.toInt() }
            .let { translate[it.position].toString() }
    }

    override fun getTheRudestTeam(): Team {
        return teams.mapNotNull { team ->
            val totalRedCards = team.players.sumOf { it.red.toInt() }
            val playerCount = team.players.size

            if (playerCount > 0) {
                team to totalRedCards.toDouble() / playerCount
            } else null
        }
            .maxByOrNull { it.second }?.first ?: teams.first()
    }

    override fun topTen(): List<Pair<String, Int>> {

        return teams.map { team ->
            val sumTransferCost = team.players.sumOf { it.transfer.toInt() }
            team.name to sumTransferCost
        }
            .sortedByDescending { it.second }
            .take(10)
    }
}