package render

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.data.category.DefaultCategoryDataset
import resolver.Resolver
import javax.swing.JFrame
import javax.swing.WindowConstants

class ChartRenderer {

    companion object {
        fun visualizeChart(resolver: Resolver) {
            val rowKey = "Transfer Cost"
            val dataset = DefaultCategoryDataset()
            resolver.getTenMostExpensiveTeams()
                .forEach { dataset.addValue(it.second.toDouble(), rowKey, it.first.name) }

            val chart = ChartFactory.createBarChart("10 Most Expensive Teams", "Team", rowKey, dataset)

            JFrame().apply {
                setSize(600, 600)
                setLocationRelativeTo(null)
                contentPane = ChartPanel(chart)
                defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
                isVisible = true
            }
        }
    }
}