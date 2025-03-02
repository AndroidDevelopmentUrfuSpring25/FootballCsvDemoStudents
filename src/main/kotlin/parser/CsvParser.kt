package parser

import model.Player
import model.Position
import model.Team
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

object CsvParser {
    fun parse(): List<Player> {
        val reader = BufferedReader(FileReader("src/main/resources/fakePlayers.csv"))
        val parsedPlayers = ArrayList<Player>()
        try {
            reader.readLine()
            while (reader.ready()) {
                val line: List<String> = reader.readLine().split(';')
                val player = Player(
                    name = line[0],
                    team = Team(line[1], line[2]),
                    position = Position.stringToPosition(line[3]),
                    nationality = line[4],
                    agency = line[5],
                    transferCost = line[6].toIntOrNull() ?: 0,
                    participations = line[7].toIntOrNull() ?: 0,
                    goals = line[8].toIntOrNull() ?: 0,
                    assists = line[9].toIntOrNull() ?: 0,
                    yellowCards = line[10].toIntOrNull() ?: 0,
                    redCards = line[11].toIntOrNull() ?: 0,
                )
                parsedPlayers.add(player)
            }
            return parsedPlayers
        } catch (e: IOException) {
            throw RuntimeException("Couldn't parse file. Error:$e")
        }
    }
}