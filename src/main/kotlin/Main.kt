import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.data.category.DefaultCategoryDataset
import resolver.Resolver
import javax.swing.JFrame


fun main(args: Array<String>) {
    val resolver = Resolver()
    Resolver().let {
        println("1. Количество игроков, интересы которых не представляет агенство -- ${it.getCountWithoutAgency()}")
        val bestDefender = resolver.getBestScorerDefender()
        println("2. Защитник ${bestDefender.first} набрал большее число голов ${bestDefender.second}")
        println("3. Самый дорогой немецкий игрок играет на позиции ${it.getTheExpensiveGermanPlayerPosition()}")
        println("4. Команда с наибольшим средним числом красных карточек на одного игрока ${it.getTheRudestTeam().name}")
    }

    val topTeams = resolver.topTeamsByTransferCost()

    val dataset = DefaultCategoryDataset()
    for (team in topTeams){
        dataset.setValue(team.second, "transferCost", team.first.name)
    }

    val chart = ChartFactory.createBarChart(
        "Top 10 teams by transfer cost",
        "Team name",
        "Transfer cost",
        dataset
    )
    val chartPanel = ChartPanel(chart)
    val frame = JFrame()
    frame.setSize(1000, 600)
    frame.contentPane = chartPanel
    frame.setLocationRelativeTo(null)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.isVisible = true
}