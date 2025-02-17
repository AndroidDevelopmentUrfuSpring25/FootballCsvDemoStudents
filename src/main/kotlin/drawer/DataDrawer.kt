package drawer

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import javax.swing.JFrame

class DataDrawer {
    fun drawPositionShareChart(positionShare: Map<String, Double>) {
        val dataset = DefaultPieDataset<String>()

        for ((position, share) in positionShare) {
            dataset.setValue(position, share)
        }

        val chart: JFreeChart = ChartFactory.createPieChart(
            "Распределение игроков по позициям",
            dataset,
            true,
            true,
            false
        )

        val plot = chart.plot as PiePlot<*>
        plot.labelGenerator = StandardPieSectionLabelGenerator("{0}: {1} ({2}%)")

        val frame = JFrame("Статистика игроков")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane = ChartPanel(chart)
        frame.pack()
        frame.isVisible = true
    }
}
