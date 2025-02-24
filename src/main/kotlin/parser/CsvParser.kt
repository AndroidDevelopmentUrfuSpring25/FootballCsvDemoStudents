package parser

import java.io.File
import model.Player
import model.Position
import model.Team

object CsvParser {
    fun readCsv(filename: String): List<Player> {
        val file = File(filename)
        val lines = file.readLines()

        val header = lines.first().split(';').map { it.trim() }
        val players = mutableListOf<Player>()

        lines.drop(1).forEach { line ->
            if (line.isBlank()) return@forEach

            val tokens = line.split(';').map { it.trim() }

            val row = header.zip(tokens).toMap()

            val team = Team(
                name = row["Team"] ?: error("No team name"),
                city = row["City"] ?: error("No city name")
            )
            val player = Player(
                name = row["Name"] ?: error("No player name"),
                team = team,
                position = Position.from(row["Position"] ?: error("No player position")),
                nationality = row["Nationality"] ?: error("No player nationality"),
                agency = row["Agency"] ?: error("No player agency"),
                transfercost = row["Transfer cost"]?.toIntOrNull() ?: 0,
                participations = row["Participations"]?.toIntOrNull() ?: 0,
                goals = row["Goals"]?.toIntOrNull() ?: 0,
                assists = row["Assists"]?.toIntOrNull() ?: 0,
                yellowCards = row["Yellow cards"]?.toIntOrNull() ?: 0,
                redCards = row["Red cards"]?.toIntOrNull() ?: 0,
            )
            players.add(player)

        }
        return players
    }
}