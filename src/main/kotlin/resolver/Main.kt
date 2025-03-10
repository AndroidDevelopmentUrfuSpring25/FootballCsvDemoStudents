package resolver

import model.Player
import parser.CsvParser
import resolver.Resolver

fun main() {
    val filePath = "src/main/resources/fakePlayers.csv"
    val players: List<Player> = CsvParser.readPlayersFromCSV(filePath)

    val resolver = Resolver(players)

    println("⚽  Количество игроков без агентства: ${resolver.getCountWithoutAgency()}")
    println("⚽  Защитник с наибольшим количеством голов: ${resolver.getBestScorerDefender()}")
    println("⚽  Позиция самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("⚽  Команда с наибольшим количеством красных карточек: ${resolver.getTheRudestTeam()?.name}")
    println("\n=================================")

    println("❇️ Вариант 2: Вывести список 10 самых дорогих команд")
    println("\n=================================")

    val top10Teams = resolver.top10TeamsByTransferValue()
    println(" Топ-10 команд с наибольшей совокупной трансферной стоимостью:")
    top10Teams.forEachIndexed { index, (team, value) ->
        println("${index + 1}. $team - ${"%,.2f".format(value)} миллионов €")

        showTop10TeamsChart(players)

    }
}
