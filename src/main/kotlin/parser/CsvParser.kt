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
                while (scanner.hasNextLine()) {
                    val currentLine: String = scanner.nextLine()
                    if (lineCounter == 0) {
                        lineCounter++
                        continue
                    }
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
        val splitCSVLine =
            csvLine.split(delimiter.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val player = Player(
            name = splitCSVLine[0],
            team = Team(splitCSVLine[1], splitCSVLine[2]),
            position = Position.from(splitCSVLine[3]),
            nationality = splitCSVLine[4],
            agency = splitCSVLine[5],
            transferCost = splitCSVLine[6].toInt(),
            participations = splitCSVLine[7].toInt(),
            goals = splitCSVLine[8].toInt(),
            assists = splitCSVLine[9].toInt(),
            yellowCards = splitCSVLine[10].toInt(),
            redCards = splitCSVLine[11].toInt(),
        )

        return player
    }
}