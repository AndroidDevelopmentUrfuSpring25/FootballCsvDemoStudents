import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.data.category.DefaultCategoryDataset
import parser.PlayerCsvParser
import resolver.Resolver
import javax.swing.JFrame
import javax.swing.WindowConstants
import kotlin.io.path.Path

fun main(args: Array<String>) {
    val pathToData = Path("src")
        .resolve("main")
        .resolve("resources")
        .resolve("fakePlayers.csv")

    val parser = PlayerCsvParser(pathToData)
    val playersData = parser.fetchData()

    val resolver = Resolver(playersData)

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam()}")

    visualizeChart(resolver)
}

private fun visualizeChart(resolver: Resolver) {
    val rowKey = "Transfer Cost"
    val dataset = DefaultCategoryDataset()
    resolver.getTenMostExpensiveTeams().forEach { dataset.addValue(it.second.toDouble(), rowKey, it.first.name) }

    val chart = ChartFactory.createBarChart("10 Most Expensive Teams", "Team", rowKey, dataset)

    JFrame().apply {
        setSize(600, 600)
        setLocationRelativeTo(null)
        contentPane = ChartPanel(chart)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isVisible = true
    }
}