package parser

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import model.Player
import model.Team
import java.io.FileReader

fun readCsv(filename: String): List<Player> {
    val players = mutableListOf<Player>()
    val csvReader = CSVReaderBuilder(FileReader(filename)).withCSVParser(
        CSVParserBuilder().withSeparator(';').build()
    ).build()

    csvReader.use { reader ->
        reader.readNext()
        var line: Array<String>? = reader.readNext()
        while (line != null) {
            val player = Player(
                name = line[0],
                team = line[1].ifEmpty { "None" },
                city = line[2],
                position = line[3],
                nationality = line[4],
                agency = line[5].ifEmpty { "None" },
                transferCost = line[6],
                participations = line[7],
                goals = line[8],
                assists = line[9],
                yellowCards = line[10],
                redCards = line[11]
            )
            players.add(player)
            line = csvReader.readNext()
        }
    }

    return players
}

fun groupPlayersByTeam(players: List<Player>): List<Team> {
    return players.groupBy { it.team }.map { (teamName, teamPlayers) ->
        Team(
            name = teamName, city = teamPlayers.firstOrNull()?.city ?: "None", players = teamPlayers
        )
    }
}