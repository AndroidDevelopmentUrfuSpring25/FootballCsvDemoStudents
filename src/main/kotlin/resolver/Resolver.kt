package resolver

import dto.player.Position
import model.Player
import model.Team
import org.apache.commons.lang3.StringUtils

class Resolver(
    private val playersData: List<Player>
) : IResolver {

    override fun getCountWithoutAgency(): Int =playersData
        .count { player -> StringUtils.isBlank(player.agency) }

    override fun getBestScorerDefender(): Pair<String, Int> = playersData
        .filter { player -> player.position == Position.DEFENDER }
        .maxBy { player -> player.goals }
        .let { bestScorer -> Pair(bestScorer.name, bestScorer.goals) }

    override fun getTheExpensiveGermanPlayerPosition(): String = playersData
        .filter { player -> player.nationality == "Germany" }
        .maxBy { player -> player.transferCost }.position.name

    override fun getTheRudestTeam(): Team = playersData
        .groupBy { player -> player.team }
        .mapValues { entry -> entry.value.map { team -> team.redCardsCount }.average() }
        .maxBy { entry -> entry.value }.key

    fun getTenMostExpensiveTeams(): List<Pair<Team, Long>> = playersData.groupBy { player -> player.team }
        .mapValues { entry -> entry.value.sumOf { team -> team.transferCost } }.entries
        .sortedByDescending { it.value }
        .map { entry -> Pair(entry.key, entry.value) }
        .take(10)
}