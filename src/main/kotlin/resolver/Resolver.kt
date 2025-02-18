package resolver

import model.Player
import model.Team

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == "" }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val playersSortedByGoals = players.sortedByDescending { it.goals }
        return  Pair(playersSortedByGoals[0].name, playersSortedByGoals[0].goals)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val theMostExpensiveGermanPlayer =
            players.filter { it.nationality == "Germany" }.maxBy { it.transferCost }

        return when (theMostExpensiveGermanPlayer.position) {
            "FORWARD" -> "Нападающий"
            "DEFENDER" -> "Защитник"
            "MIDFIELD" -> "Полузащитник"
            "GOALKEEPER" -> "Вратарь"
            else -> ""

        }
    }

    override fun getTheRudestTeam(): Team {
        val teams = mutableListOf<Team>()

        val mapOfTeams = players.groupBy { it.team }
        mapOfTeams.forEach {entry ->
            val team = Team(entry.key, entry.value.toList())
            teams.add(team)
        }

        val mapOfTeamsAndAverageRedCards = emptyMap<String, Float>().toMutableMap()
        teams.forEach {
            val averageAmountOfRedCards = it.players.sumOf { it.redCards }.toFloat() / it.players.count().toFloat()
            mapOfTeamsAndAverageRedCards[it.teamName] = averageAmountOfRedCards
        }
        val theRudestTeamName = mapOfTeamsAndAverageRedCards.maxBy { it.value }.key
        val theRudestTeam = teams.find { it.teamName == theRudestTeamName }
        return theRudestTeam!!
    }
}