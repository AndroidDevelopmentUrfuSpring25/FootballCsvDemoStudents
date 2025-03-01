package drawer

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import java.text.DecimalFormat
import javax.swing.JFrame


class ChartDrawer {

    fun drawChart(players: List<Player>) {
        val chartPanel = ChartPanel(getChart(players))
        val frame = JFrame()
        frame.setSize(800, 600)
        frame.contentPane = chartPanel
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = true
    }

    private fun getChart(players: List<Player>): JFreeChart {
        val dataset: DefaultPieDataset<String> = DefaultPieDataset()
        players.groupBy { it.nationality }
            .forEach { dataset.setValue(it.key, it.value.count()) }
        val chart = ChartFactory.createPieChart(
            "Доля игроков из разных стран",
            dataset,
            true,
            true,
            false
        )
        val percentFormat = DecimalFormat("0.0%")
        val plot = chart.plot as PiePlot<*>
        plot.labelGenerator = StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})",
            DecimalFormat("0"),
            percentFormat
        )
        return chart

    }
}