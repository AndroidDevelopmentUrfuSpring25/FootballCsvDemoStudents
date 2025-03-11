package parser

import model.Player
import model.Team
import model.PlayerPosition
import java.io.File

object CsvParser {
    fun readPlayersFromCSV(filePath: String): List<Player> {
        return File(filePath).readLines()
            .drop(1)
            .mapNotNull { line ->
                val parts = line.split(";")

                if (parts.size < 12) return@mapNotNull null

                Player(
                    name = parts[0],
                    team = Team(name = parts[1], city = parts[2]),
                    nationality = parts[4],
                    position = parts[3].takeIf { it.isNotBlank() }
                        ?.let { runCatching { enumValueOf<PlayerPosition>(it.uppercase()) }.getOrNull() }
                        ?: PlayerPosition.MIDFIELDER,
                    agency = parts[5].ifBlank { null },
                    transferValue = parts[6].toDoubleOrNull() ?: 0.0,
                    matchesPlayed = parts[7].toIntOrNull() ?: 0,
                    goals = parts[8].toIntOrNull() ?: 0,
                    assists = parts[9].toIntOrNull() ?: 0,
                    yellowCards = parts[10].toIntOrNull() ?: 0,
                    redCards = parts[11].toIntOrNull() ?: 0
                )
            }
    }
}
