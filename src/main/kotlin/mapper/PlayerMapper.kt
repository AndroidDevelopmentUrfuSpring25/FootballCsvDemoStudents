package mapper

import dto.player.Position
import model.Player
import model.Team
import org.apache.commons.lang3.StringUtils

class PlayerMapper {

    companion object {
        fun fetchDto(columns: List<String>): Player {
            return Player(
                columns[0],
                Team(columns[1], columns[2]),
                Position.valueOf(columns[3]),
                columns[4],
                if (StringUtils.isBlank(columns[5])) null else columns[5],
                columns[6].toLong(),
                columns[7].toInt(),
                columns[8].toInt(),
                columns[9].toInt(),
                columns[10].toInt(),
                columns[11].toInt()
            )
        }
    }
}