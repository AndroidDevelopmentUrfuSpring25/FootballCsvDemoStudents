package resolver

import model.Player
import model.PositionTranslation
import model.Team

/**
 * Решает задачи
 */
class Resolver(
    private val playerList: List<Player>,
    private val teamList: List<Team>
) : IResolver {

    override fun getCountWithoutAgency(): Int {
        var countWithoutAgency = 0
        playerList.forEach { player ->
            if (player.agency == null || player.agency == "")
                countWithoutAgency++
        }
        return countWithoutAgency
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val defenderPlayers = playerList.filter { it.positionOnField == PositionTranslation.DEFENDER }
        return defenderPlayers
            .maxBy { it.goals ?: 0 }
            .let { Pair( it.name ?: "No Name", it.goals ?: 0) }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val germanPlayers = playerList.filter { it.nationality == "Germany" }
        if(germanPlayers.isEmpty())
            throw Exception("Нет подходящих кандидатов")

        return germanPlayers
            .maxBy { it.transferCost ?: 0 }.positionOnField.translation
    }

    override fun getTheRudestTeam(): Team {

        val rudestTeam = teamList.maxByOrNull { team ->
            val players = team.getPlayers()
            val countRedCards: Float = players
                .sumOf { player -> player.redCards ?: 0 }
                .toFloat()
            countRedCards / players.size
        }
        rudestTeam ?: throw Exception("Нет подходящих команд")
        return rudestTeam
    }

    fun getDistributionPositions(): Map<PositionTranslation, Int> {
        return playerList.groupingBy { it.positionOnField }
            .eachCount()
            .withDefault { 0 }
    }

}