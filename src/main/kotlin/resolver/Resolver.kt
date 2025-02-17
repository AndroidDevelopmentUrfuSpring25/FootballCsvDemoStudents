package resolver
import model.Player
import model.Team
import parser.CsvParser
import java.util.Scanner

class Resolver(
    private val players: List<Player> = CsvParser.readPlayers("main")
) : IResolver{

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter{it.position == "DEFENDER"}.maxByOrNull { it.goals }?.let { Pair(it.name, it.goals)} ?: run { throw RuntimeException() }

    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { "Germany" == it.nationality}.maxByOrNull { it.transferCost }?.position?: throw  RuntimeException();
    }

    override fun getTheRudestTeam(): Team {
        val teamsGrouped: Map<Team, List<Player>> = players.groupBy { it.team }
        val teamWithHighestAvg = teamsGrouped.maxByOrNull { (_, teamPlayers) ->
            teamPlayers.map { it.redCards }.average()
        }?.key ?: throw RuntimeException()
        return teamWithHighestAvg
    }
}