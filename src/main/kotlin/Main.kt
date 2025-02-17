package resolver

import parser.CsvParser

fun main() {
    // Считываем список игроков из CSV-файла
    val players = CsvParser.readPlayers("src/main/resources/fakePlayers.csv")

    // Создаем экземпляр Resolver, передавая список игроков
    val resolver = Resolver(players)

    // 1. Количество игроков без агентства
    val countWithoutAgency = resolver.getCountWithoutAgency()
    println("Количество игроков без агентства: $countWithoutAgency")

    // 2. Защитник с наибольшим числом голов и его количество
    val (defenderName, goals) = resolver.getBestScorerDefender()
    println("Лучший защитник (наибольшее число голов): $defenderName, Голы: $goals")

    // 3. Русское название позиции самого дорогого немецкого игрока
    val russianPosition = resolver.getTheExpensiveGermanPlayerPosition()
    println("Позиция самого дорогого немецкого игрока (на русском): $russianPosition")

    // 4. Команда с наибольшим числом удалений на одного игрока
    val rudestTeam = resolver.getTheRudestTeam()
    println("Команда с наибольшим числом удалений на одного игрока: ${rudestTeam.name} (${rudestTeam.city})")
}