import model.Team
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.CategoryLabelPositions
import org.jfree.chart.axis.NumberAxis
import org.jfree.data.category.DefaultCategoryDataset
import java.io.File
import java.text.DecimalFormat

object ChartRender {
    fun saveTopTeamsChart(teamTransferCosts: Map<Team, Int>, fileName: String) {
        val dataset = DefaultCategoryDataset()
        teamTransferCosts.entries.sortedByDescending { it.value }
            .take(10)
            .forEach { (team, transferCost) ->
                dataset.addValue(transferCost, "Трансферная стоимость", team.name)
            }

        val chart: JFreeChart = ChartFactory.createBarChart(
            "Вариант 2. Топ-10 команд с наивысшей суммарной трансферной стоимостью",
            "Команды",
            "Трансферная стоимость всех игроков команды",
            dataset
        )

        val yAxis = chart.categoryPlot.rangeAxis as NumberAxis
        yAxis.numberFormatOverride = DecimalFormat("#,###")

        chart.categoryPlot.domainAxis.categoryLabelPositions = CategoryLabelPositions.UP_45

        val chartFile = File(fileName)
        ChartUtils.saveChartAsPNG(chartFile, chart, 1200, 600)
        println("График сохранен в файл '$fileName'")
    }

}
