package visualization

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import javax.swing.JFrame

object ChartVisualizer {
    fun showForwardVsCost(forwards: List<Player>) {
        val series = XYSeries("Кол-во голов")
        forwards.forEach { player ->
            series.add(
                player.transfercost.toDouble(),
                player.goals.toDouble()
            )
        }

        val dataset = XYSeriesCollection().apply { addSeries(series) }

        val chart: JFreeChart = ChartFactory.createXYLineChart(
            "Зависимость голов от трансферной стоимости для нападающих",
            "Трансферная стоимость",
            "Забитые голы",
            dataset
        )

        val chartpanel = ChartPanel(chart)
        val frame = JFrame("График для Нападающих")
        frame.contentPane = chartpanel
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }
}