package parser

import model.Player
import java.io.File

object CsvParser {
    fun readPlayersFromCsv(filePath: String): List<Player> {
        return File(filePath).readLines()
            .drop(1)
            .map { line ->
                val parts = line.split(";")

                Player(
                    name = parts[0],
                    team = parts[1],
                    position = parts[3],
                    nationality = parts[4],
                    agency = if (parts[5].isNotEmpty()) parts[5] else null,
                    transferValue = parts[6].toDouble(),
                    matches = parts[7].toInt(),
                    goals = parts[8].toInt(),
                    assists = parts[9].toInt(),
                    yellowCards = parts[10].toInt(),
                    redCards = parts[11].toInt()
                )
            }
    }
}
