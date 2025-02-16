import model.Position
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import parser.CsvParser
import resolver.Resolver
import javax.swing.JFrame


fun main(args: Array<String>) {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")

    Resolver(players).also {
        println("Количество игроков, интересы которых не представляет агенство: ${it.getCountWithoutAgency()}")
        println("Автор наибольшего числа голов из числа защитников и их количество: ${it.getBestScorerDefender()}")
        println("Русское название позиции самого дорогого немецкого игрока: ${it.getTheExpensiveGermanPlayerPosition()}")
        println("Команда с наибольшим средним числом красных карточек на одного игрока: ${it.getTheRudestTeam().name}")
    }

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