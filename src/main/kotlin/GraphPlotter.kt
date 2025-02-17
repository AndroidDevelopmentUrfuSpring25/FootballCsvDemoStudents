import model.PositionTranslation
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.data.general.DefaultPieDataset
import javax.swing.JFrame

/**
 * Отображет графики
 */
object GraphPlotter {
    /**
     * Отображает круговую диаграмму по словарю
     */
    fun createPieChart(data: Map<PositionTranslation, Int>) {
        val dataset = DefaultPieDataset<String>().apply {
            data.forEach { (key, value) -> setValue(key.translation, value.toDouble()) }
        }

        val chart = ChartFactory.createPieChart(
            "Распределение по позициям",
            dataset,
            true,
            true,
            false
        )

        val frame = JFrame("Круговая диаграмма").apply {
            contentPane = ChartPanel(chart)
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            setSize(800, 600)
            isVisible = true  // Это нужно для того, чтобы окно отображалось
        }
    }
}

