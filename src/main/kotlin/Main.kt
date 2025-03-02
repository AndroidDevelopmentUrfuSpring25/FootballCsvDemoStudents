import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.data.category.DefaultCategoryDataset
import printer.ChartPrinter
import resolver.Resolver
import javax.swing.JFrame


fun main(args: Array<String>) {
    val chartPrinter = ChartPrinter()
    Resolver().let {
        println("1. Количество игроков, интересы которых не представляет агенство -- ${it.getCountWithoutAgency()}")
        val bestDefender = it.getBestScorerDefender()
        println("2. Защитник ${bestDefender.first} набрал большее число голов ${bestDefender.second}")
        println("3. Самый дорогой немецкий игрок играет на позиции ${it.getTheExpensiveGermanPlayerPosition()}")
        println("4. Команда с наибольшим средним числом красных карточек на одного игрока ${it.getTheRudestTeam().name}")
        chartPrinter.print(it.topTeamsByTransferCost())
    }
}