import parser.groupPlayersByTeam
import parser.readCsv
import resolver.Resolver
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.CategoryPlot
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.renderer.category.BarRenderer
import org.jfree.data.category.DefaultCategoryDataset
import java.awt.Color
import javax.swing.JFrame


fun main() {
    val players = readCsv("src/main/resources/fakePlayers.csv")
    val teams = groupPlayersByTeam(players)
    val resolver = Resolver(teams)
    println("Игроков без агентсва: ${resolver.getCountWithoutAgency()}")
    val (bestDefender, goals) = resolver.getBestScorerDefender()
    println("Лучший защитник: $bestDefender, голов: $goals")
    val expensive = resolver.getTheExpensiveGermanPlayerPosition()
    println("Роль Самого высокооплачиваемого игрока: $expensive")
    val rude = resolver.getTheRudestTeam().name
    println("Самая грубая команда: $rude")
    val topTeams = resolver.topTen()

    val dataset = DefaultCategoryDataset()
    for ((teamName, totalCost) in topTeams) {
        dataset.addValue(totalCost, "Трансферная стоимость", teamName)
    }

    val chart: JFreeChart = ChartFactory.createBarChart(
        "Топ-10 команд по трансферной стоимости",
        "Команды",
        "Суммарная стоимость",
        dataset,
        PlotOrientation.VERTICAL,
        true, true, false
    )

    val plot: CategoryPlot = chart.categoryPlot
    val renderer = BarRenderer()
    renderer.setSeriesPaint(0, Color.BLUE)
    plot.renderer = renderer

    val frame = JFrame("График трансферных стоимостей")
    frame.contentPane = ChartPanel(chart)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
}



