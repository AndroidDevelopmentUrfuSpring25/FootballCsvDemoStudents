package drawer

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import javax.swing.JFrame

class Drawer {

    // Метод для построения графика зависимости голов от стоимости для нападающих
    fun drawGoalsVsTransferValueForForwards(players: List<Player>) {
        // Фильтруем игроков, оставляем только нападающих
        val forwards = players.filter { it.position == "FORWARD" }

        // Создаем серию данных для графика
        val series = XYSeries("Goals vs Transfer Value (Forwards)")

        // Заполняем данные для графика только для нападающих
        forwards.forEach { player ->
            series.add(player.transferValue, player.goals) // X: трансферная стоимость, Y: количество голов
        }

        // Создаем коллекцию данных
        val dataset = XYSeriesCollection(series)

        // Создаем график с использованием ChartFactory
        val chart: JFreeChart = ChartFactory.createXYLineChart(
            "Goals vs Transfer Value (Forwards)", // Заголовок графика
            "Transfer Value",          // Ось X
            "Goals",                   // Ось Y
            dataset                    // Данные
        )

        // Создаем окно для отображения графика
        val frame = JFrame("JFreeChart Example")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.add(ChartPanel(chart)) // Добавляем график в панель
        frame.pack()
        frame.isVisible = true
    }
}