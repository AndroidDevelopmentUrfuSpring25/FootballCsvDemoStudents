package parser

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import java.io.File

object ChartCreator {

    fun createPieChart(nationalityData: Map<String, Double>, outputPath: String) {
        val dataset = DefaultPieDataset<String>()
        nationalityData.forEach { (country, percentage) ->
            dataset.setValue(country, percentage)
        }

        val chart: JFreeChart = ChartFactory.createPieChart(
            "Доля игроков из разных стран", // Заголовок
            dataset, // Данные
            true,    // Легенда
            true,    // Подсказки
            false    // URL-ссылки
        )

        // Настройка отображения процентов
        val plot = chart.plot as PiePlot<*>
        plot.isCircular   = true
        plot.labelGenerator = org.jfree.chart.labels.StandardPieSectionLabelGenerator(
            "{0}: ({1}%)" // {0} - категория, {1} - значение, {2} - процент
        )

        plot.simpleLabels = false // Для аккуратного расположения подписей

        // Сохранение диаграммы как изображения (PNG)
        val chartFile = File(outputPath)
        ChartUtils.saveChartAsPNG(chartFile, chart, 800, 600)

        println("Диаграмма с процентами сохранена в $outputPath")

    }
}