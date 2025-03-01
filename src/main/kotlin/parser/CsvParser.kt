package parser

import model.*
import java.io.FileReader

object CsvParser {
    private var players: MutableList<Player> = mutableListOf()

    fun parse(path: String): List<Player> {
        val file = FileReader(path)
        file.readLines()
            .drop(1)
            .forEach { players.add(parsePlayer(it)) }
        return players
    }

    private fun parsePlayer(playerString: String): Player {
        val playerParts = playerString.split(";")
        return Player(
            name = playerParts[0],
            team = Team(name = playerParts[1], city = playerParts[2]),
            position = Position.getByValue(playerParts[3]),
            nationality = playerParts[4],
            agency = playerParts[5],
            transferCost = playerParts[6].toInt(),
            participationCount = playerParts[7].toInt(),
            goalsCount = playerParts[8].toInt(),
            assistsCount = playerParts[9].toIntOrNull().orZero(),
            yellowCardsCount = playerParts[10].toIntOrNull().orZero(),
            redCardsCount = playerParts[11].toIntOrNull().orZero()
        )
    }

    private fun Int?.orZero() = this ?: 0
}