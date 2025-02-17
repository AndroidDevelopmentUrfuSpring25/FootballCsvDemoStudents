package resolver

import model.Player
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    // Количество игроков, интересы которых не представляет агенство.
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == null }
    }

    // Автор наибольшего числа голов из числа защитников и их количество.
    override fun getBestScorerDefender(): Pair<String, Int> {
        val defenders = players.filter { it.position.equals("DEFENDER", ignoreCase = true) }
        val bestDefender = defenders.maxByOrNull { it.goals }
            ?: throw NoSuchElementException("Защитники не найдены.")
        return bestDefender.name to bestDefender.goals
    }

    // Русское название позиции самого дорогого немецкого игрока.
    override fun getTheExpensiveGermanPlayerPosition(): String {
        val germanPlayers = players.filter { it.nationality.equals("Germany", ignoreCase = true) }
        val mostExpensiveGerman = germanPlayers.maxByOrNull { it.transferCost }
            ?: throw NoSuchElementException("Немецкие игроки не найдены.")

        val positionMap = mapOf(
            "goalkeeper" to "Вратарь",
            "defender" to "Защитник",
            "midfield" to "Полузащитник",
            "forward" to "Нападающий"
        )
        val englishPosition = mostExpensiveGerman.position.lowercase()
        return positionMap[englishPosition] ?: mostExpensiveGerman.position
    }

    // Команду с наибольшим числом удалений на одного игрока.

    override fun getTheRudestTeam(): Team {

        val teamsGrouped = players.groupBy { it.team }
        val teamWithHighestAvg = teamsGrouped.maxByOrNull {
            (_, player) -> player.map { it.redCards }.average()
        }
        return teamWithHighestAvg?.key ?: throw NoSuchElementException("Команды не найдены")
    }
}
