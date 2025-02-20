package visaualization

import model.Player
import model.Team
import resolver.Resolver

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JFrame

class Visualization(private val players: List<Player>) {
    val resolver = Resolver(players)
    val teams: List<Pair<Team, Int>> = resolver.getTheMostStransferCostTeam()

    fun makeVisualization(): JFreeChart {
        val dataset = createDataset(teams)

        return ChartFactory.createBarChart(
            "Топ-10 команд с наивысшей суммарной трансферной стоимостью",
            "Команды",
            "Суммарная трансферная стоимость",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        )

    }

    fun showChart(chart: JFreeChart) {
        val frame = JFrame("Bar Chart Example")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane = ChartPanel(chart)
        frame.setSize(1000, 1000)
        frame.isVisible = true
    }


    private fun createDataset(teams: List<Pair<Team, Int>>): CategoryDataset {
        val dataset = DefaultCategoryDataset()

        for ((team, totalValue) in teams) {
            dataset.addValue(totalValue.toDouble(), "Суммарная стоимость", team.name)
        }

        return dataset
    }
}