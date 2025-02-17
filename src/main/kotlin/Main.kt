package resolver

import parser.CsvParser

fun main() {

    val players = CsvParser.readPlayers("src/main/resources/fakePlayers.csv")


    val resolver = Resolver(players)

    val countWithoutAgency = resolver.getCountWithoutAgency()
    println("Количество игроков без агентства: $countWithoutAgency")

    val (defenderName, goals) = resolver.getBestScorerDefender()
    println("Лучший защитник (наибольшее число голов): $defenderName, Голы: $goals")

    val russianPosition = resolver.getTheExpensiveGermanPlayerPosition()
    println("Позиция самого дорогого немецкого игрока (на русском): $russianPosition")

    val rudestTeam = resolver.getTheRudestTeam()
    println("Команда с наибольшим числом удалений на одного игрока: ${rudestTeam.name} (${rudestTeam.city})")
}