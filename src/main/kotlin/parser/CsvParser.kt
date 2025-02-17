package parser
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.File
import java.nio.charset.StandardCharsets
import model.Player
import model.Team

fun readCsv(filePath: String): List<Player> {
    val file = File(filePath)
    val player = mutableListOf<Player>()

    file.reader(StandardCharsets.UTF_8).use { reader ->
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader())

        for (record in csvParser) {
            val person = Player(
                name = record["Name"] ?: "Unknown",
                team = record["Team"] ?: "Unknown",
                city = record["City"] ?: "Unknown",
                position = record["Position"] ?: "Unknown",
                nationality = record["Nationality"] ?: "Unknown",
                agency = record["Agency"] ?: "Unknown",
                transfer = record["Transfer cost"] ?: "Unknown",
                participation = record["Participations"] ?: "Unknown",
                goals = record["Goals"] ?: "Unknown",
                assists = record["Assists"] ?: "Unknown",
                yellow = record["Yellow cards"] ?: "Unknown",
                red = record["Red cards"] ?: "Unknown"
            )
            player.add(person)
        }
    }

    return player
}
fun groupPlayersByTeam(players: List<Player>): List<Team> {
    return players.groupBy { it.team }
        .map { (teamName, teamPlayers) ->
            Team(
                name = teamName,
                city = teamPlayers.firstOrNull()?.city ?: "Unknown",
                players = teamPlayers
            )
        }
}

fun main() {
    val players = readCsv("src/main/resources/fakePlayers.csv")
    val teams = groupPlayersByTeam(players)
    teams.forEach { team ->
        println("Команда: ${team.name} (${team.city})")
        team.players.forEach { player ->
            println("  - ${player.name}, позиция: ${player.position}")
        }
        println()
    }
    players.forEach { println(it) }
}

