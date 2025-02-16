package parser

import model.Player
import model.Position
import model.Team
import java.io.File

object CsvParser {

    private var players: MutableList<Player> = mutableListOf()

    fun parse(path: String): List<Player> {
        File(path).readLines().drop(1).forEach { parsePlayerFromString(it) }
        return players
    }

    private fun parsePlayerFromString(playerString: String) {
        val playerParts = playerString.split(";")

        players.add(
            Player(
                name = playerParts[0],
                team = Team(name = playerParts[1], city = playerParts[2]),
                position = Position.valueOf(playerParts[3]),
                nationality = playerParts[4],
                agency = playerParts[5],
                transferCost = playerParts[6].toIntOrNull().orZero(),
                participationCount = playerParts[7].toIntOrNull().orZero(),
                goalsCount = playerParts[8].toIntOrNull().orZero(),
                assistsCount = playerParts[9].toIntOrNull().orZero(),
                yellowCardsCount = playerParts[10].toIntOrNull().orZero(),
                redCardsCount = playerParts[11].toIntOrNull().orZero()
            )
        )
    }

    private fun Int?.orZero() = this ?: 0
}
