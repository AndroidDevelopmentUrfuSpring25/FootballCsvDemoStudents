package resolver

import model.Player
import model.Team
import parser.CsvParser

class Resolver(
    private val players: List<Player> = CsvParser.getPlayersFromFile()
) : IResolver {
    override fun getCountWithoutAgency(): Int {

        var count = 0
        for (player in players) {
            if (player.agency.isNullOrEmpty()) {
                count += 1
            }
        }

        return count
    }

    override fun getBestScorerDefender(): Pair<String, Int> {

        var author = ""
        var goalsCount = 0

        for (player in players) {
            if (player.position == "DEFENDER" && goalsCount < player.goals) {
                author = player.name
                goalsCount = player.goals
            }
        }

        return Pair(author, goalsCount)
    }

    override fun getTheMostExpensiveGermanPlayerPosition(): String {

        var position = ""
        var mostExpensiveCost = 0L

        for (player in players) {
            if (player.nationality == "Germany" && mostExpensiveCost < player.transferCost) {
                position = player.position
                mostExpensiveCost = player.transferCost
            }
        }

        return when (position) {
            "GOALKEEPER" -> "Вратарь"
            "DEFENDER" -> "Защитник"
            "MIDFIELD" -> "Полузащитник"
            "FORWARD" -> "Нападающий"
            else -> "Неизвестная позиция игрока!"
        }
    }

    override fun getTheRudestTeam(): Team {

        val teamMap = mutableMapOf<String, MutableList<Player>>()

        for (player in players) {
            if (!teamMap.containsKey(player.team)) {
                teamMap[player.team] = mutableListOf()
            }
            teamMap[player.team]!!.add(player)
        }

        var rudestTeam: Team? = null
        var maxAverageRedCards = -1.0

        for ((teamName, teamPlayers) in teamMap) {

            val totalRedCards = teamPlayers.sumOf { it.redCards }
            val averageRedCards =
                if (teamPlayers.isNotEmpty()) totalRedCards.toDouble() / teamPlayers.size
                else 0.0

            if (averageRedCards > maxAverageRedCards) {
                maxAverageRedCards = averageRedCards
                rudestTeam = Team(teamName, teamPlayers.first().city, teamPlayers)
            }
        }

        return rudestTeam ?: throw IllegalStateException("No teams found")
    }

    override fun getPositionShare(): Map<String, Double> {
        val total = players.size.toDouble()
        val positionCounts = mutableMapOf<String, Int>()
        for (player in players) {
            positionCounts[player.position] = positionCounts.getOrDefault(player.position, 0) + 1
        }
        return positionCounts.mapValues { it.value / total }
    }
}