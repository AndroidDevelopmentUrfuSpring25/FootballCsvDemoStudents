import drawer.ChartDrawer
import parser.CsvParser
import resolver.Resolver


fun main() {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")

    Resolver(players).also {
        println("Количество игроков, интересы которых не представляет агенство: ${it.getCountWithoutAgency()}")
        println("Автор наибольшего числа голов из числа защитников и их количество: ${it.getBestScorerDefender()}")
        println("Русское название позиции самого дорогого немецкого игрока: ${it.getTheExpensiveGermanPlayerPosition()}")
        println("Команда с наибольшим средним числом красных карточек на одного игрока: ${it.getTheRudestTeam().name}")
    }

    ChartDrawer.drawChart(players)
}