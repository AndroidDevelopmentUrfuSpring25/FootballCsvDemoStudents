package parser
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.File
import model.Player
import model.Position
import model.Team

object CsvParser {
    fun readCsv(filePath: String): List<Player> {
        val teams = mutableMapOf<String, Team>()
        val players = mutableListOf<Player>()

        val file = File(filePath)
        val reader = file.bufferedReader()
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader())

        for (record in csvParser) {
            val teamName = record["Team"]
            val city = record["City"]
            val team = teams.getOrPut(teamName) { Team(teamName, city) }
            val position = Position.valueOf(record["Position"])
            val player = Player(
                name = record["Name"] ?: "",
                team = team,
                position = position,
                nationality = record["Nationality"] ?: "",
                agency = record["Agency"] ?: "",
                transfer = record["Transfer cost"]?.toIntOrNull() ?: 0,
                participation = record["Participations"]?.toIntOrNull() ?: 0,
                goals = record["Goals"]?.toIntOrNull() ?: 0,
                assists = record["Assists"]?.toIntOrNull() ?: 0,
                yellow = record["Yellow cards"]?.toIntOrNull() ?: 0,
                red = record["Red cards"]?.toIntOrNull() ?: 0
            )

            players.add(player)
        }

        csvParser.close()
        return players
    }
}



