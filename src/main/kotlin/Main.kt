import drawer.ChartDrawer
import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {
    val parser = CsvParser
    val players = parser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)
    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam().name}")
    ChartDrawer().drawChart(players)
}