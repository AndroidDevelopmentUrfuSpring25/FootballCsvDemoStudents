package printer

import model.Team
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.data.category.DefaultCategoryDataset
import javax.swing.JFrame

class ChartPrinter {
    fun print(teams: List<Pair<Team, Int>>) {
        val dataset = DefaultCategoryDataset()
        for (team in teams){
            dataset.setValue(team.second, "transferCost", team.first.name)
        }

        val chart = ChartFactory.createBarChart(
            "Top 10 teams by transfer cost",
            "Team name",
            "Transfer cost",
            dataset
        )

        val chartPanel = ChartPanel(chart)
        val frame = JFrame()
        frame.setSize(1000, 600)
        frame.contentPane = chartPanel
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = true
    }
}