package parser

import model.Player
import java.io.File
import java.io.FileNotFoundException
import java.util.Scanner

object CsvParser {
    fun getPlayersFromFile(): List<Player> {
        val players = mutableListOf<Player>()

        try {
            val file = File("src/main/resources/fakePlayers.csv")
            Scanner(file).use { scanner ->

                if (scanner.hasNextLine()) {
                    scanner.nextLine()
                }

                while (scanner.hasNextLine()) {
                    val line = scanner.nextLine()
                    val player = parsePlayerFromLine(line)
                    players.add(player)
                }
            }
        } catch (e: FileNotFoundException) {
            throw RuntimeException("Файл не найден: ${e.message}", e)
        }

        return players
    }

    private fun parsePlayerFromLine(line: String): Player {
        val delimiter = ";"
        val parts = line.split(delimiter)

        return Player(
            parts[0], parts[1], parts[2], parts[3], parts[4],
            parts[5],
            parts[6].toLong(), parts[7].toInt(), parts[8].toInt(),
            parts[9].toInt(), parts[10].toInt(), parts[11].toInt()
        )
    }
}