import java.io.File
import model.*

object CsvParser {
    fun parse(filePath: String): MutableList<Player> {
        val players = mutableListOf<Player>()
        val lines = File(filePath).readLines()

        // Пропускаем заголовок
        for (line in lines.drop(1)) {
            val tokens = line.split(";")
            if (tokens.size < 11) continue

            val team = Team(
                name = tokens[1],
                city = tokens[2]
            )

            val player = Player(
                name = tokens[0],
                team = team,
                position = tokens[3],
                nationality = tokens[4],
                agency = tokens[5],
                transferValue = tokens[6].toDoubleOrNull() ?: 0.0,
                matchesPlayed = tokens[7].toIntOrNull() ?: 0,
                goals = tokens[8].toIntOrNull() ?: 0,
                assists = tokens[9].toIntOrNull() ?: 0,
                yellowCards = tokens[10].toIntOrNull() ?: 0,
                redCards = tokens[11].toIntOrNull() ?: 0
            )
            players.add(player)
        }
        return players
    }
}
