package parser

import model.*
import java.io.File

object CsvParser {
    val address = "src/main/resources/fakePlayers.csv"

    fun getText (): List<Player> {
        var listOfPlayers = mutableListOf<Player>()
        for (line in File(address).readLines().drop(1)){
            val properties = line.split(";")
            val currTeam = Team(
                name = properties[1],
                city = properties[2]
            )
            var currentPlayer = Player(
                name = properties[0],
                team = currTeam,
                position = getRusPosition(properties[3]),
                nationality = properties[4],
                agency = properties[5],
                transferCost = properties[6].toInt(),
                participations = properties[7].toInt(),
                goals = properties[8].toInt(),
                assists = properties[9].toInt(),
                yellowCards = properties[10].toInt(),
                redCards = properties[11].toInt())
            listOfPlayers.add(currentPlayer)
        }
        return listOfPlayers
    }

    private fun getRusPosition(engPosition: String): String {
        val position = Position()
        return position.mapOfPositions[engPosition]?: throw IllegalArgumentException("Position not found")
    }
}