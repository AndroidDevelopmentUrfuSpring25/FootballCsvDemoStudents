package resolver

import model.Player
import model.Team

class Resolver(val players: List<Player>) : IResolver{

    // Выведите количество игроков, интересы которых не представляет агенство.
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isBlank() }
    }

    // Выведите автора наибольшего числа голов из числа защитников и их количество.
    override fun getBestScorerDefender(): Pair<String, Int>{
        val defenders = players.filter{it.position.contains("defender", ignoreCase = true)}

        val bestDefender = defenders.maxByOrNull { it.goals }
        return bestDefender?.let { it.name to it.goals } ?: Pair("", 0)
    }

    // Выведите русское название позиции самого дорогого немецкого игрока.
    override fun getTheExpensiveGermanPlayerPosition(): String{
        val germanPlayers = players.filter{it.nationality.equals("Germany", ignoreCase = true)}

        val expensivePlayer = germanPlayers.maxByOrNull { it.transfercost } ?: return ""

        val positionTranslations = mapOf(
            "GOALKEEPER" to "Вратарь",
            "DEFENDER" to "Защитник",
            "MIDFIELD" to "Полузащитник",
            "FORWARD" to "Нападающий"
        )
        return positionTranslations[expensivePlayer.position] ?: expensivePlayer.position
    }

    // Выберите команду с наибольшим числом удалений на одного игрока.
    override fun getTheRudestTeam(): Team{
        val teamAvgRedCards = players.groupBy { it.team }
            .maxByOrNull { it.value.map {player -> player.red_cards}.average() }
        return teamAvgRedCards?.key ?: error("No teams found")
    }
}