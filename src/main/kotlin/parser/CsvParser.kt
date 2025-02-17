package parser

import model.Player
import java.io.File

object CsvParser {
    fun parsePlayers(filePath: String): List<Player> {
        return File(filePath).readLines()
            .drop(1)
            .mapNotNull { line ->
                val parts = line.split(";")
                if (parts.size < 12) return@mapNotNull null
                Player(
                    name = parts[0].trim(),
                    team = parts[1].trim(),
                    city = parts[2].trim(),
                    position = parts[3].trim(),
                    nationality = parts[4].trim(),
                    agency = parts[5].trim().takeIf { it.isNotEmpty() },
                    transferCost = parts[6].trim().toIntOrNull() ?: 0,
                    participations = parts[7].trim().toIntOrNull() ?: 0,
                    goals = parts[8].trim().toIntOrNull() ?: 0,
                    assists = parts[9].trim().toIntOrNull() ?: 0,
                    yellowCards = parts[10].trim().toIntOrNull() ?: 0,
                    redCards = parts[11].trim().toIntOrNull() ?: 0
                )
            }
    }
}