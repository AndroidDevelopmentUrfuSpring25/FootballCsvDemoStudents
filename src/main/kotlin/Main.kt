import model.Position
import parser.CsvParser
import resolver.Resolver

fun main() {
    val players = CsvParser.readCsv("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим числом удалений на одного игрока: ${resolver.getTheRudestTeam().name}")

    val forwards = players.filter { it.position == Position.FORWARD }

    visualization.ChartVisualizer.showForwardVsCost(forwards)
}
