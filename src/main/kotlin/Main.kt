import model.Player
import resolver.Resolver
import java.io.File

fun readCsvManually(filePath: String): List<Player> {
    val lines = File(filePath).readLines()
    if (lines.isEmpty()) return emptyList()

    val players = mutableListOf<Player>()
    for (line in lines.drop(1)) {
        val parts = line.split(';')
        if (parts.size < 12) continue

        val player = Player(
            name = parts[0],
            team = parts[1],
            city = parts[2],
            position = parts[3],
            nationality = parts[4],
            agency = parts[5],
            transferCost = parts[6].toLong(),
            participations = parts[7].toInt(),
            goals = parts[8].toInt(),
            assists = parts[9].toInt(),
            yellowCards = parts[10].toInt(),
            redCards = parts[11].toInt()
        )
        players.add(player)
    }
    return players
}

fun main(args: Array<String>) {
    val players = readCsvManually("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    println("количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")

    println("автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")

    println("русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")

    val theRudestTeam = resolver.getTheRudestTeam()
    println("команду с наибольшим средним числом красных карточек на одного игрока:${theRudestTeam} (Название ${theRudestTeam.teamName})")
}