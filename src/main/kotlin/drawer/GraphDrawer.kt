package drawer

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import java.awt.Dimension
import javax.swing.JFrame

class GraphDrawer {

    fun drawGoalsCostDependencyForForwards(forwards: List<Player>) {

        val series = XYSeries("Голы / Трансферная стоимость")

        forwards.forEach {
            series.add(it.transferCost, it.goals)
        }

        val chart = ChartFactory.createXYLineChart(
            "Зависимость количества забитых голов от трансферной стоимости для нападающих",
            "Трансферная стоимость",
            "Кол-во забитых голов",
            XYSeriesCollection(series)
        )

        val frame = JFrame("JFreeChart Example")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = true
        frame.add(ChartPanel(chart))
        frame.size = Dimension(1280, 1024)
        frame.setLocationRelativeTo(null)
    }
}