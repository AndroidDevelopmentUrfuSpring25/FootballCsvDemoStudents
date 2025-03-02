package resolver

import model.Player
import model.Position
import model.Team
import parser.CsvParser

class Resolver(private val players: List<Player> = CsvParser.parse()) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { Position.DEFENDER == it.position }.maxByOrNull { it.goals }
            ?.let { Pair(it.name, it.goals) } ?: run { throw RuntimeException("Не найдено ни одного игрока") }

    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }?.position?.posName
            ?: throw RuntimeException("Не найдено ни одного игрока")
    }

    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .mapValues { entry -> entry.value.map { it.redCards }.average() }
            .maxByOrNull { it.value }?.key
            ?: throw RuntimeException("Не найдено ни одной команды")
    }


    override fun topTeamsByTransferCost(): List<Pair<Team, Int>> {
        return players.groupBy { it.team }
            .map { (team, players) -> team to players.sumOf { it.transferCost } }
            .sortedByDescending { it.second }
            .take(10)
    }
}