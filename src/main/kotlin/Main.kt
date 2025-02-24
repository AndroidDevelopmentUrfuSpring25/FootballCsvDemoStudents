import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.data.general.DefaultPieDataset
import parser.groupPlayersByTeam
import parser.readCsv
import resolver.Resolver
import javax.swing.JFrame

fun main() {
    val players = readCsv("src/main/resources/fakePlayers.csv")
    val teams = groupPlayersByTeam(players)
    val resolver = Resolver(teams)
    println("Игроки без агентства: ${resolver.getCountWithoutAgency()}")
    val (bestDefender, goals) = resolver.getBestScorerDefender()
    println("Защитник с болшим числом голов: $bestDefender, голов: $goals")
    val expensive = resolver.getTheExpensiveGermanPlayerPosition()
    println("Позиция cамого высокооплачиваемого игрока: $expensive")
    val rude = resolver.getTheRudestTeam().name
    println("Самая грубая команда: $rude")
    val positionsRate = resolver.positionRate()
    val dataset = DefaultPieDataset<String>()

    //создаем набор данных для датасета
    for ((posName, rate) in positionsRate) {
        dataset.setValue(posName, rate)
    }

    //создаем чарт
    val chart: JFreeChart = ChartFactory.createPieChart(
        "Доля игроков по позициям",
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