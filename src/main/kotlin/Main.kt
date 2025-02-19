package resolver

import model.Player
import parser.CsvParser

fun main() {
    val filePath = "src/main/resources/fakePlayers.csv"
    val players: List<Player> = CsvParser.readPlayersFromCSV(filePath)

    val resolver = Resolver()


    println("⚽  Количество игроков без агентства: ${resolver.countPlayersWithoutAgency(players)}")
    println("⚽  Защитник с наибольшим количеством голов: ${resolver.topScoringDefender(players)}")
    println("⚽  Позиция самого дорогого немецкого игрока: ${resolver.mostExpensiveGermanPlayerPosition(players)}")
    println("⚽  Команда с наибольшим количеством красных карточек: ${resolver.teamWithMostRedCards(players)}")
    println("\n=================================")

    println("❇️Вариант 2: Вывести список 10 самых дорогих команд")
    println("\n=================================")

    val top10Teams = resolver.top10TeamsByTransferValue(players)
    println(" Топ-10 команд с наибольшей совокупной трансферной стоимостью:")
    top10Teams.forEachIndexed { index, (team, value) ->
        println("${index + 1}. $team - $value миллионов €")
    }
}
