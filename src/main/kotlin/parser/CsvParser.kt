package parser

import model.Player
import model.Team

object CsvParser {
    fun parsePlayers(fileName: String): List<Player> {
        val inputStream = CsvParser::class.java.getResourceAsStream("/$fileName")

        return inputStream!!.bufferedReader().useLines { lines ->
            val iterator = lines.iterator()
            if (iterator.hasNext()) {
                iterator.next()
            }
            val players = mutableListOf<Player>()
            while (iterator.hasNext()) {
                val line = iterator.next()
                if (line.isBlank()) continue

                val columns = line.split(";")

                val name = columns[0].trim()
                val teamName = columns[1].trim()
                val city = columns[2].trim()
                val position = columns[3].trim()
                val nationality = columns[4].trim()
                val agency = columns[5].trim().ifEmpty { null }
                val transferCost = columns[6].trim().toIntOrNull() ?: 0
                val participations = columns[7].trim().toIntOrNull() ?: 0
                val goals = columns[8].trim().toIntOrNull() ?: 0
                val assists = columns[9].trim().toIntOrNull() ?: 0
                val yellowCards = columns[10].trim().toIntOrNull() ?: 0
                val redCards = columns[11].trim().toIntOrNull() ?: 0

                val team = Team(teamName, city)
                val player = Player(
                    name = name,
                    team = team,
                    position = position,
                    nationality = nationality,
                    agency = agency,
                    transferCost = transferCost,
                    participations = participations,
                    goals = goals,
                    assists = assists,
                    yellowCards = yellowCards,
                    redCards = redCards
                )
                players.add(player)
            }
            players
        }
    }
}
