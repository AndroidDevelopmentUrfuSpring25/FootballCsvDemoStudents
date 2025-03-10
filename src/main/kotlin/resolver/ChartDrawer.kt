package resolver

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.CategoryPlot
import org.jfree.chart.axis.CategoryAxis
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.renderer.category.BarRenderer
import org.jfree.chart.axis.CategoryLabelPositions
import org.jfree.data.category.DefaultCategoryDataset
import java.awt.Color
import java.awt.Font
import javax.swing.JFrame
import model.Player
import java.text.DecimalFormat

fun showTop10TeamsChart(players: List<Player>) {
    val dataset = DefaultCategoryDataset()


    val top10Teams = players.groupBy { it.team }
        .mapValues { (_, teamPlayers) -> teamPlayers.sumOf { it.transferValue } }
        .toList()
        .sortedByDescending { it.second }
        .take(10)


    for ((team, value) in top10Teams) {
        dataset.addValue(value / 1_000_000, "Трансферная стоимость (М€)", team.name)
    }


    val chart: JFreeChart = ChartFactory.createBarChart(
        "10 самых дорогих команд",
        "Команда",
        "Стоимость (М€)",
        dataset
    )


    val plot = chart.categoryPlot as CategoryPlot
    val renderer = plot.renderer as BarRenderer


    renderer.setSeriesPaint(0, Color(255, 99, 99))
    renderer.setShadowVisible(true)
    renderer.setDrawBarOutline(true)
    plot.backgroundPaint = Color(240, 240, 240)
    plot.rangeGridlinePaint = Color.GRAY


    val domainAxis = plot.domainAxis as CategoryAxis
    domainAxis.labelFont = Font("Arial", Font.BOLD, 14)
    domainAxis.categoryLabelPositions = CategoryLabelPositions.UP_45

    val rangeAxis = plot.rangeAxis as NumberAxis
    rangeAxis.tickLabelFont = Font("Arial", Font.PLAIN, 12)
    rangeAxis.numberFormatOverride = DecimalFormat("#,### M€")


    chart.title.font = Font("Arial", Font.BOLD, 18)


    val frame = JFrame("График")
    frame.contentPane = ChartPanel(chart)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
}
