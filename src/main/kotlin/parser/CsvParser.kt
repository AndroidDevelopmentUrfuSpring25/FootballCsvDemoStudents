package parser

import model.Player
import java.io.File

object CsvParser {
    fun readPlayersFromCSV(filePath: String): List<Player> {
        return File(filePath).readLines()
            .drop(1) // İlk satırı atla (başlık satırı)
            .map { line ->
                val parts = line.split(";") // NOKTALI VİRGÜL AYIRICI
                Player(
                    name = parts[0],
                    team = parts[1],
                    city = parts[2],
                    position = parts[3],
                    nationality = parts[4],
                    agency = parts[5].ifBlank { null },
                    transferValue = parts[6].toDouble(),
                    matchesPlayed = parts[7].toInt(),
                    goals = parts[8].toInt(),
                    assists = parts[9].toInt(),
                    yellowCards = parts[10].toInt(),
                    redCards = parts[11].toInt()
                )
            }
    }
}
