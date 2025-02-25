fun main() {
    println("Кайаал Яхья Вариант 1")
    println("=================================")

    val players = parser.CsvParser.readPlayersFromCsv("src/main/resources/fakePlayers.csv")

    println("\n📊 Распределение игроков по позициям:")
    val totalPlayers = players.size
    val positionCounts = players.groupingBy { it.position }.eachCount()
    positionCounts.forEach { (position, count) ->
        val percentage = (count.toDouble() / totalPlayers) * 100
        println("$position: %.2f%%".format(percentage))
    }

    println("\n=================================")

    println("1️⃣ Количество игроков без агентства: ${resolver.Resolver().countPlayersWithoutAgency(players)}")

    val topDefender = resolver.Resolver().topScoringDefender(players)
    println("2️⃣ Лучший защитник по голам: ${topDefender?.first} (${topDefender?.second} голов)")

    val expensiveGermanPosition = resolver.Resolver().mostExpensiveGermanPlayerPosition(players)
    println("3️⃣ Позиция самого дорогого немецкого игрока: ${expensiveGermanPosition ?: "Нет данных"}")

    val mostRedCardsTeam = resolver.Resolver().teamWithMostRedCards(players)
    println("4️⃣ Команда с наибольшим средним количеством красных карточек: ${mostRedCardsTeam ?: "Нет данных"}")

    println("=================================")
}
