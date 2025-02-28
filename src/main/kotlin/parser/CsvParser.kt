package parser

import helpers.orZero
import model.Player
import model.PlayerPosition
import model.Team
import java.io.File

object CsvParser {
    fun parse(fileName: String): List<Map<String, String>> {
        val file = File(fileName)

        val lines = file.readLines().map { line -> line.split(";") }
        val headers = lines[0]

        val parsedObject = lines.subList(1, lines.size).map { line ->
            headers.zip(line).toMap()
        }

        return parsedObject
    }

    fun getPlayers(fileName: String): List<Player> = this.parse(fileName).map { row ->
        Player(
            name = row["Name"].orEmpty(),
            team = Team(
                name = row["Team"].orEmpty(),
                city = row["City"].orEmpty()
            ),
            position = PlayerPosition.valueOf(row["Position"].orEmpty()),
            nationality = row["Nationality"].orEmpty(),
            agency = row["Agency"].orEmpty(),
            transferCost = row["Transfer cost"]?.toIntOrNull().orZero(),
            participations = row["Participations"]?.toIntOrNull().orZero(),
            goals = row["Goals"]?.toIntOrNull().orZero(),
            assists = row["Assists"]?.toIntOrNull().orZero(),
            yellowCards = row["Yellow cards"]?.toIntOrNull().orZero(),
            redCards = row["Red cards"]?.toIntOrNull().orZero()
        )
    }
}