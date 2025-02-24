package resolver

import model.Team

class Resolver(private val teamList: List<Team>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return teamList
            .flatMap { it.players }
            .count { it.agency == "None" }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val player = teamList
            .flatMap { it.players }
            .filter { it.position == "DEFENDER" }
            .maxBy { it.goals.toInt() }

        return Pair(player.name!!, player.goals.toInt())
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return teamList
            .flatMap { it.players }
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost.toInt() }
            .let {
                when (it?.position) {
                    "DEFENDER" -> "Защитник"
                    "FORWARD" -> "Нападающий"
                    "GOALKEEPER" -> "Вратарь"
                    else -> "None"
                }
            }
    }

    override fun getTheRudestTeam(): Team {
        return teamList.maxBy { team ->
            val redCardsCount = team.players.sumOf { it.redCards.toInt() }
            val playerCount = team.players.size
            redCardsCount / playerCount
        }
    }

    fun positionRate(): Map<String, Int> {
        val dict = teamList
            .flatMap { it.players }
            .groupBy { it.position }

        val rateDict = HashMap<String, Int>()
        for (key in dict.keys) {
            rateDict[key] = dict[key]!!.size
        }

        return rateDict
    }

}