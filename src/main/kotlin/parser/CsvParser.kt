package parser

import model.Player
import model.Team
import model.Position
import java.io.File

object CsvParser {
    fun parse(filePath: String): List<Player> {
        val lines = File(filePath).readLines().drop(1)

        val players = mutableListOf<Player>()

        lines.forEach { line ->
            val fields = line.split(";").map { it.trim() }

            if (fields.size != 12) {
                throw IllegalArgumentException("Invalid player data")
            }

            val player = Player(
                name = fields[0],
                team = Team(name = fields[1], city = fields[2]),
                position = Position.parse(fields[3]),
                nationality = fields[4],
                agency = fields[5],
                transferCost = fields[6].toIntOrNull() ?: 0,
                participations = fields[7].toIntOrNull() ?: 0,
                goals = fields[8].toIntOrNull() ?: 0,
                assists = fields[9].toIntOrNull() ?: 0,
                yellowCards = fields[10].toIntOrNull() ?: 0,
                redCards = fields[11].toIntOrNull() ?: 0
            )

            players.add(player)
        }

        return players
    }
}
