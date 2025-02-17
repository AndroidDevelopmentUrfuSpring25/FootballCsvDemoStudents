package chart

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.data.category.DefaultCategoryDataset
import javax.swing.JFrame
import org.jfree.chart.plot.CategoryPlot
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.axis.CategoryAxis
import org.jfree.chart.axis.CategoryLabelPositions
import java.text.NumberFormat

class ChartDrawer {
    fun draw(playersData: List<Player>){
        val topTeams = playersData.groupBy { it.team }
        .mapValues { (team, teamPlayers) -> teamPlayers.sumOf { it.transferCost } }
        .toList()
        .sortedByDescending { it.second }
        .take(10)

        val dataset = DefaultCategoryDataset()
        topTeams.forEach { (team, transferCost) ->
            dataset.addValue(transferCost, "Трансферная стоимость", team.name)
        }

        val chart: JFreeChart = ChartFactory.createBarChart(
            "Топ-10 команд с наивысшей суммарной трансферной стоимостью",
            "Команда",
            "Трансферная стоимость",
            dataset
        )

        val plot: CategoryPlot = chart.plot as CategoryPlot
        val yAxis = plot.rangeAxis as NumberAxis
        yAxis.numberFormatOverride = NumberFormat.getInstance()

        val xAxis: CategoryAxis = plot.domainAxis as CategoryAxis
        xAxis.categoryLabelPositions = CategoryLabelPositions.createUpRotationLabelPositions(45.0)

        val panel = ChartPanel(chart)
        val frame = JFrame("Топ-10 команд с наивысшей суммарной трансферной стоимостью")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane.add(panel)
        frame.pack()
        frame.isVisible = true
    }
}