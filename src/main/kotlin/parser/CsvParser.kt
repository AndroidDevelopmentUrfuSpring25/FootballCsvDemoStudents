package parser
import java.io.File
import model.Player
import model.Team

object CsvParser{
    fun readPlayers(filePath: String): List<Player> {
        val players = mutableListOf<Player>()

        val lines = File(filePath).readLines()

        for (line in lines.drop(1)) {
            val playerData = line.split(";")
            val team = Team(playerData[1], playerData[2])
            val player = Player(
                name = playerData[0],
                team = team,
                position = playerData[3],
                nationality = playerData[4],
                agency = playerData[5].ifEmpty { null },
                transferCost = playerData[6].toDouble(),
                matchesPlayed = playerData[7].toInt(),
                goals = playerData[8].toInt(),
                assists = playerData[9].toInt(),
                yellowCards = playerData[10].toInt(),
                redCards = playerData[11].toInt()
            )
            players.add(player)
        }

        return players
    }

}