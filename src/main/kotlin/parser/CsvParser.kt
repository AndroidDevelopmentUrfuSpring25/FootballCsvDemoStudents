package parser

import model.Player
import model.Team
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

object CsvParser {
    private val filePath: String = "src/main/resources/fakePlayers.csv"
    val bufferedReader: BufferedReader = BufferedReader(FileReader(File(filePath)))
    val playersList: MutableList<Player> = mutableListOf()


    fun parseCsv(): MutableList<Player> {

        val csvParser = CSVParser(
            bufferedReader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withDelimiter(';')
                .withTrim()
        )


        for (csvRecord in csvParser) {
            val playerName = csvRecord.get("Name")
            val team = csvRecord.get("Team")
            val city = csvRecord.get("City")
            val nationality = csvRecord.get("Nationality")
            val position = csvRecord.get("Position")
            val agency = csvRecord.get("Agency")
            val transferCost = csvRecord.get("Transfer cost").toIntOrNull() ?: 0
            val participations = csvRecord.get("Participations").toIntOrNull() ?: 0
            val goals = csvRecord.get("Goals").toIntOrNull() ?: 0
            val assists = csvRecord.get("Assists").toIntOrNull() ?: 0
            val yellowCards = csvRecord.get("Yellow cards").toIntOrNull() ?: 0
            val redCards = csvRecord.get("Red cards").toIntOrNull() ?: 0

            val currentTeam: Team = Team(team, city)
            val player: Player = Player(
                playerName,
                currentTeam,
                nationality,
                position,
                agency,
                transferCost,
                participations,
                goals,
                assists,
                yellowCards,
                redCards
            )

            playersList.add(player)
        }
        csvParser.close()
        bufferedReader.close()

        return playersList
    }
}