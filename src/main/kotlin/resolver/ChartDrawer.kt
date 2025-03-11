package resolver

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.CategoryPlot
import org.jfree.chart.renderer.category.BarRenderer
import org.jfree.data.category.DefaultCategoryDataset
import java.awt.Color
import java.awt.Font
import javax.swing.JFrame
import model.Player

object ChartDrawer {
    fun showPositionDistributionChart(players: List<Player>) {
        val dataset = DefaultCategoryDataset()

        // Oyuncu pozisyonlarına göre yüzdelik hesapla
        val positionDistribution = players.groupingBy { it.position.name }.eachCount()
        val totalPlayers = players.size.toDouble()

        positionDistribution.forEach { (position, count) ->
            val percentage = (count / totalPlayers) * 100
            dataset.addValue(percentage, "позиция", position)
        }

        // Bar Chart oluştur
        val chart: JFreeChart = ChartFactory.createBarChart(
            "Распределение позиций игроков", // Başlık
            "позиция", // X Ekseni (Kategori)
            "процентиль (%)", // Y Ekseni (Değer)
            dataset
        )

        // Grafik özelleştirmeleri
        val plot = chart.categoryPlot as CategoryPlot
        val renderer = plot.renderer as BarRenderer

        // Çubuk rengi ayarla
        renderer.setSeriesPaint(0, Color(79, 129, 189)) // Mavi çubuklar

        // Arkaplan ve çizgi renkleri
        plot.backgroundPaint = Color(240, 240, 240)
        plot.rangeGridlinePaint = Color.GRAY

        // Başlık ve yazı tipleri
        chart.title.font = Font("Arial", Font.BOLD, 18)
        plot.domainAxis.labelFont = Font("Arial", Font.BOLD, 14)
        plot.rangeAxis.labelFont = Font("Arial", Font.PLAIN, 12)

        // Pencere oluştur ve grafiği göster
        val frame = JFrame("Oyuncu Pozisyon Dağılımı")
        frame.contentPane = ChartPanel(chart)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.isVisible = true
    }
}
