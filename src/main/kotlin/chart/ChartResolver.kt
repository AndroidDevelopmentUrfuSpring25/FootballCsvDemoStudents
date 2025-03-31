package chart

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.data.general.DefaultPieDataset
import javax.swing.JFrame

class ChartResolver(private val title: String, private val players: List<Player>) {
    private fun positionRate(): Map<String, Int> {
        val dict = players.groupBy { it.position }

        val rateDict = HashMap<String, Int>()
        for (key in dict.keys) {
            rateDict[key.name] = dict[key]!!.size
        }

        return rateDict
    }

    fun drawChart() {
        val dataset = DefaultPieDataset<String>()


        //создаем набор данных для датасета
        for ((posName, rate) in positionRate()) {
            dataset.setValue(posName, rate)
        }

        //создаем чарт
        val chart: JFreeChart = ChartFactory.createPieChart(
            title,
            dataset,
            true,
            true,
            false
        )

        val chartPanel = ChartPanel(chart)
        chartPanel.preferredSize = java.awt.Dimension(800, 600)
        val frame = JFrame("Pie Chart")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane = chartPanel
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }
}