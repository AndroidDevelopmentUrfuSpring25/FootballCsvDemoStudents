package parser

import model.Player
import model.Position
import model.Team
import java.io.File
import java.io.FileNotFoundException
import java.util.Scanner


object CsvParser {
    fun getWordsFromFile(): List<Player> {
        val players: MutableList<Player> = ArrayList()
        try {
            var lineCounter = 0
            Scanner(File("src/main/resources/fakePlayers.csv")).use { scanner ->
                scanner.nextLine()
                while (scanner.hasNextLine()) {
                    val currentLine: String = scanner.nextLine()
                    lineCounter++
                    players.add(getRecordFromLine(currentLine))
                }
            }
        } catch (e: FileNotFoundException) {
            throw RuntimeException(e)
        }

        return players
    }

    private fun getRecordFromLine(csvLine: String): Player {
        val delimiter = ";"
        val splitCSVLine = csvLine.split(delimiter)
        val player = Player(
            name = splitCSVLine[0],
            team = Team(splitCSVLine[1], splitCSVLine[2]),
            position = Position.from(splitCSVLine[3]),
            nationality = splitCSVLine[4],
            agency = splitCSVLine[5],
            transferCost = splitCSVLine[6].toIntOrNull() ?: 0,
            participations = splitCSVLine[7].toIntOrNull() ?: 0,
            goals = splitCSVLine[8].toIntOrNull() ?: 0,
            assists = splitCSVLine[9].toIntOrNull() ?: 0,
            yellowCards = splitCSVLine[10].toIntOrNull() ?: 0,
            redCards = splitCSVLine[11].toIntOrNull() ?: 0,
        )

        return player
    }
}