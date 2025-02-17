import parser.CsvParser
import resolver.Resolver
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import javax.swing.JFrame

fun main(){
    val players = CsvParser.readCsv("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим числом удалений на одного игрока: ${resolver.getTheRudestTeam().name}")

    val forwards = players.filter { it.position.contains("forward", ignoreCase = true) }

    val series = XYSeries("Стоимость нападающего")
    forwards.forEach{ player -> series.add(player.transfercost.toDouble(), player.goals.toDouble())}

    val dataset = XYSeriesCollection().apply { addSeries(series) }

    val chart: JFreeChart = ChartFactory.createXYLineChart(
        "Зависимость голов от трансферной стоимости для нападающих",
        "Трансферная стоимость",
        "Забитые голы",
        dataset)

    val chartpanel = ChartPanel(chart)
    val frame = JFrame("График для Нападающих")
    frame.contentPane = chartpanel
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.isVisible = true
}
