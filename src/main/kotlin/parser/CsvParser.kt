package parser

import model.*
import model.PlayerPosition
import java.io.File

object CsvParser {
    private const val address = "src/main/resources/fakePlayers.csv"

    fun getText(): List<Player> {
        val listOfPlayers = mutableListOf<Player>()
        File(address).readLines().drop(1).forEach {
            val properties = it.split(";")
            val currentTeam = Team(
                name = properties[1],
                city = properties[2]
            )
            val currentPlayer = Player(
                name = properties[0],
                team = currentTeam,
                position = PlayerPosition.valueOf(properties[3].orEmpty()),
                nationality = properties[4],
                agency = properties[5],
                transferCost = properties[6].toInt(),
                participations = properties[7].toInt(),
                goals = properties[8].toInt(),
                assists = properties[9].toInt(),
                yellowCards = properties[10].toInt(),
                redCards = properties[11].toInt()
            )
            listOfPlayers.add(currentPlayer)
        }
        return listOfPlayers
    }
}