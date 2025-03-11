package resolver

import model.Player
import parser.CsvParser

fun main() {
    val filePath = "src/main/resources/fakePlayers.csv"
    val players: List<Player> = CsvParser.readPlayersFromCSV(filePath)

    val resolver = Resolver(players)

    println("⚽  Количество игроков без агентства: ${resolver.getCountWithoutAgency()}")
    println("⚽  Защитник с наибольшим количеством голов: ${resolver.getBestScorerDefender()}")
    println("⚽  Позиция самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("⚽  Команда с наибольшим количеством красных карточек: ${resolver.getTheRudestTeam()}")
    println("\n=================================")

    println("❇ Вариант 1: Распределение игроков по позициям")
    println("\n=================================")

    resolver.calculatePositionDistribution().forEach { (position, percentage) ->
        println("$position: %.2f%%".format(percentage))
    }
    ChartDrawer.showPositionDistributionChart(players)

}
