package parser

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import enums.Position
import extensions.IntExtensions.orZero
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
                team = Team(line[1], line[2]),
                position = Position.valueOf(line[3]),
                nationality = line[4],
                agency = line[5],
                transferCost = line[6].toIntOrNull().orZero(),
                participations = line[7].toIntOrNull().orZero(),
                goals = line[8].toIntOrNull().orZero(),
                assists = line[9].toIntOrNull().orZero(),
                yellowCards = line[10].toIntOrNull().orZero(),
                redCards = line[11].toIntOrNull().orZero()
            )
            players.add(player)
            line = csvReader.readNext()
        }
    }

    return players
}
