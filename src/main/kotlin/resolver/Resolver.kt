package resolver

import model.Player
import model.Position
import model.Team
import org.apache.commons.lang3.NotImplementedException
import parser.CsvParser

class Resolver(
    private val players: List<Player> = CsvParser.getWordsFromFile()
) : IResolver {
    override fun getCountWithoutAgency(): Int =
        players.count { it.agency.isEmpty() || it.agency.isBlank() }

    override fun getBestScorerDefender(): Pair<String, Int> =
        players.filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { Pair(it.name, it.goals) } ?: throw NotImplementedException("No player found")

    override fun getTheMostExpensiveGermanPlayerPosition(): String =
        players.filter { "Germany" == it.nationality }
            .maxByOrNull { it.transferCost }
            ?.position?.russianPositionName ?: throw NotImplementedException("No player found")

    override fun getTheRudestTeam(): Team =
        players.maxByOrNull { it.redCards }?.team
            ?: throw NotImplementedException("No player found")

    override fun getNationalityShare(): Map<String, Double> =
        players.groupingBy { it.nationality }.eachCount()
            .mapValues { (_, eachNationalityCount) ->
                (eachNationalityCount.toDouble() / players.size) * 100
            }
}