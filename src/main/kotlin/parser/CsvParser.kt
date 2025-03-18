package parser

import model.Player
import model.PlayerPosition
import model.Team
import java.io.File

object CsvParser {
    fun parsePlayers(filePath: String): List<Player> {
        val lines = File(filePath).readLines()
        val players = mutableListOf<Player>()

        for (i in 1 until lines.size) {
            val line = lines[i]
            val tokens = line.split(";")

            players.add(
                Player(
                    name = tokens[0],
                    team = Team(tokens[1], tokens[2]),
                    position = tokens[3].takeIf { it.isNotBlank() }
                        ?.let { runCatching { enumValueOf<PlayerPosition>(it.uppercase()) }.getOrNull() }
                        ?: PlayerPosition.MIDFIELDER,
                    nationality = tokens[4],
                    agency = if (tokens[5].isEmpty()) null else tokens[5],
                    transferCost = tokens[6].toInt(),
                    participations = tokens[7].toInt(),
                    goals = tokens[8].toInt(),
                    assists = tokens[9].toInt(),
                    yellowCards = tokens[10].toInt(),
                    redCards = tokens[11].toInt()
                )
            )
        }

        return players
    }
}