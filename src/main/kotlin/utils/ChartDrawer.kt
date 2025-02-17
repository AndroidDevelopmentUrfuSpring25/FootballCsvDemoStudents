package utils

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import javax.swing.JFrame

object ChartDrawer {
    fun createAndShowChart(nationalityShare: Map<String, Double>) {
        val chart = createChart(nationalityShare)
        val chartPanel = ChartPanel(chart)
        val frame = JFrame("Диаграмма доли игроков из разных стран.")

        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane.add(chartPanel)
        frame.pack()
        frame.isVisible = true
    }

    private fun createChart(nationalityShare: Map<String, Double>): JFreeChart {
        val dataset = DefaultPieDataset<String>()

        for ((nationality, share) in nationalityShare) {
            dataset.setValue(nationality, share)
        }

        val chart = ChartFactory.createPieChart(
            "Доля национальностей игроков",
            dataset,
            true,
            true,
            false
        )

        val plot = chart.plot as PiePlot<*>
        plot.labelGenerator = StandardPieSectionLabelGenerator("{0}: {2}")

        return chart
    }
}