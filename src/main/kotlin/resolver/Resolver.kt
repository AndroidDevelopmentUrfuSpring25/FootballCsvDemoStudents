package resolver

import model.Player
import model.Team

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot
import org.jfree.chart.axis.CategoryLabelPositions
import org.jfree.data.category.DefaultCategoryDataset
import java.io.File
import java.text.DecimalFormat

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == null }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val defendersPlayers = players.filter { it.position == "DEFENDER" }
        return defendersPlayers.maxBy { it.goals }.let { it.name to it.goals }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensiveGermanPlayer = players.filter { it.nationality == "Germany" }
            .maxBy { it.transferCost }
        return when (expensiveGermanPlayer.position) {
            "MIDFIELD" -> "Полузащитник"
            "DEFENDER" -> "Защитник"
            "FORWARD" -> "Нападающий"
            "GOALKEEPER" -> "Вратарь"
            else -> ""
        }
    }

    override fun getTheRudestTeam(): Team {
        val teamMap = players.groupBy { it.team }

        var rudestTeamName: String? = null
        var rudestTeamPlayers: List<Player>? = null
        var maxAverageRedCards = 0.0

        for ((teamName, teamPlayers) in teamMap) {
            val averageRedCards =  teamPlayers.sumOf { it.redCards } .toDouble() / teamPlayers.size

            if (averageRedCards > maxAverageRedCards) {
                rudestTeamName = teamName
                rudestTeamPlayers = teamPlayers
                maxAverageRedCards = averageRedCards
            }
        }

        return Team(name = rudestTeamName ?: "", players = rudestTeamPlayers ?: emptyList())
    }

    fun getTopTeamsByTransferCost(fileName: String) {
        val teamTransferCosts = players.groupBy { it.team }
            .mapValues { (_, teamPlayers) ->
                teamPlayers.sumOf { it.transferCost }
            }

        val topTeams = teamTransferCosts.entries.sortedByDescending { it.value }
            .take(10)

        val dataset = DefaultCategoryDataset()
        topTeams.forEach { (team, transferCost) ->
            dataset.addValue(transferCost, "Трансферная стоимость", team)
        }

        val chart: JFreeChart = ChartFactory.createBarChart(
            "Вариант 2. Топ-10 команд с наивысшей суммарной трансферной стоимостью",
            "Команды",
            "Трансферная стоимость всех игроков команды",
            dataset
        )

        val yAxis = chart.categoryPlot.rangeAxis as NumberAxis
        yAxis.numberFormatOverride = DecimalFormat("#,###")

        chart.categoryPlot.domainAxis.categoryLabelPositions = CategoryLabelPositions.UP_45

        val chartFile = File(fileName)
        ChartUtils.saveChartAsPNG(chartFile, chart, 1200, 600)
        println("Топ-10 команд с наивысшей суммарной трансферной стоимостью сохранены в файл '$fileName'")
    }
}