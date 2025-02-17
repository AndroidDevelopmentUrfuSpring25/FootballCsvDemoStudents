import model.Player
import model.PositionTranslation
import model.Team

/**
 * Обрабатывает и хранит данные о футболистах и командах
 */
class FootballManager(
    footballData: List<Map<NameParameters, String>>
) {
    private val playerList = mutableListOf<Player>()
    private val teamMap = mutableMapOf<String, Team>()

    init {
        for (footballerData in footballData) {

            val player = Player(
                name = footballerData[NameParameters.NAME],
                positionOnField = typifyPosition(
                    footballerData[NameParameters.POSITION],
                    footballerData[NameParameters.NAME]
                ),
                nationality = footballerData[NameParameters.NATIONALITY],
                agency = footballerData[NameParameters.AGENCY],
                transferCost = footballerData[NameParameters.TRANSFER_COST]?.toIntOrNull(),
                participations = footballerData[NameParameters.PARTICIPATIONS]?.toIntOrNull(),
                goals = footballerData[NameParameters.GOALS]?.toIntOrNull(),
                assists = footballerData[NameParameters.ASSISTS]?.toIntOrNull(),
                yellowCards = footballerData[NameParameters.YELLOW_CARDS]?.toIntOrNull(),
                redCards = footballerData[NameParameters.RED_CARDS]?.toIntOrNull()
            )
            playerList.add(player)

            val nameTeam = footballerData[NameParameters.TEAM]
            nameTeam?.let { teamName ->
                val team = teamMap.getOrPut(teamName) {
                    Team(
                        name = teamName,
                        city = footballerData[NameParameters.CITY]
                    )
                }
                team.addPlayer(player)
            }
        }
    }

    private fun typifyPosition(stringPosition: String?, name: String?): PositionTranslation {
        stringPosition ?: throw Exception("Позиция игрока $name не найдена")
        val position = PositionTranslation.entries.find {
            it.name == (stringPosition.uppercase())
        } ?: throw Exception("Неизвестная позиция $stringPosition")
        return position
    }

    fun getPlayerList(): List<Player> = playerList.toList()

    fun getTeamList(): List<Team> = teamMap.values.toList()
}