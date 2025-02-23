package mapper

import dto.player.Position
import model.Player
import model.Team

class PlayerMapper {

    companion object {
        fun fetchDto(columns: List<String>): Player {
            return Player(
                name = columns[0],
                team = Team(name = columns[1], city = columns[2]),
                position = Position.getOrDefault(columns[3]),
                nationality = columns[4],
                agency = if (columns[5].isBlank()) null else columns[5],
                transferCost = columns[6].toLongOrNull() ?: 0,
                participationCount = columns[7].toIntOrNull() ?: 0,
                goals = columns[8].toIntOrNull() ?: 0,
                assists = columns[9].toIntOrNull() ?: 0,
                yellowCardsCount = columns[10].toIntOrNull() ?: 0,
                redCardsCount = columns[11].toIntOrNull() ?: 0
            )
        }
    }
}