package resolver

import ChartRender
import model.Player
import model.PlayerPosition
import model.Team

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.axis.CategoryLabelPositions
import org.jfree.data.category.DefaultCategoryDataset
import java.io.File
import java.text.DecimalFormat

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency?.isBlank() == true }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val defendersPlayers = players.filter { it.position == PlayerPosition.DEFENDER }
        return defendersPlayers.maxByOrNull { it.goals }
            ?.let { it.name to it.goals }
            ?: ("No one" to 0)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensiveGermanPlayer = players.filter { it.nationality == "Germany" }
            .maxBy { it.transferCost }
        return when (expensiveGermanPlayer.position) {
            PlayerPosition.MIDFIELDER -> "Полузащитник"
            PlayerPosition.DEFENDER -> "Защитник"
            PlayerPosition.FORWARD -> "Нападающий"
            PlayerPosition.GOALKEEPER -> "Вратарь"
        }
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
            .maxByOrNull { (_, teamPlayers) -> teamPlayers.map { it.redCards }.average() }
            ?.key ?: Team("", "")
    }

    fun getTopTeamsByTransferCost(fileName: String) {
        val teamTransferCosts = players.groupBy { it.team }
            .mapValues { (_, teamPlayers) -> teamPlayers.sumOf { it.transferCost } }

        ChartRender.saveTopTeamsChart(teamTransferCosts, fileName)
    }
}