package drawer

import model.Player
import model.Position
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import javax.swing.JFrame

class ChartDrawer {
    companion object {
        fun drawChart(players: List<Player>) {
            val series = XYSeries("Забитые голы vs трансферная стоимость для нападающих")
            players
                .filter { it.position == Position.FORWARD }
                .forEach { series.add(it.transferCost, it.goalsCount) }

            val chart = ChartFactory.createXYLineChart(
                "Зависимость количества забитых голов от трансферной стоимости для нападающих",
                "Трансферная стоимость",
                "Количество забитых голов",
                XYSeriesCollection().apply { addSeries(series) }
            )

            JFrame().apply {
                setSize(1000, 600)
                setLocationRelativeTo(null)
                contentPane = ChartPanel(chart)
                defaultCloseOperation = JFrame.EXIT_ON_CLOSE
                isVisible = true
            }
        }
    }
}