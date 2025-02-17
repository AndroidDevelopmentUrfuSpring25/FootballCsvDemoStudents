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
    ): IResolver{

    override fun getCountWithoutAgency(): Int {
        var countWithoutAgency: Int = 0
        for(player in playerList){
            if(player.agency == null || player.agency == "")
                countWithoutAgency++
        }
        return countWithoutAgency
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        var maxGoals:Int = 0
        var bestScorer:String = "No Name"
        for(player in playerList){
            if(player.positionOnField == PositionTranslation.DEFENDER)
                if(maxGoals < (player.goals ?: 0)){
                    bestScorer = player.name ?: "No Name"
                    maxGoals = player.goals ?: 0
                }
        }
        return Pair(bestScorer, maxGoals)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        var maxCost:Int = 0
        var expensiveGermanPlayer:Player? = null

        for(player in playerList){
            if(player.nationality == "Germany")
                if(maxCost < (player.transferCost ?: 0)){
                    expensiveGermanPlayer = player
                    maxCost = player.transferCost ?: 0
                }
        }
        expensiveGermanPlayer?: throw Exception("Нет подходящих кандидатов")
        return expensiveGermanPlayer.positionOnField.translation
    }

    override fun getTheRudestTeam(): Team {
        var maxAverageRemoved: Float = 0f
        var rudestTeam:Team? = null
        for(team in teamList){
            var teamRemoved:Float = 0f
            for(player in team.getPlayers()){
                teamRemoved += player.redCards ?: 0
            }

            val teamAverageRemoved = teamRemoved / team.getPlayers().size
            if(maxAverageRemoved < teamAverageRemoved){
                maxAverageRemoved = teamAverageRemoved
                rudestTeam = team
            }
        }
        rudestTeam ?: throw Exception("Нет подходящих команд")
        return rudestTeam
    }

    fun getDistributionPositions():Map<PositionTranslation, Int>{
        val numberPlayersInPositions = PositionTranslation.entries.associateWith{0}.toMutableMap()
        for(player in playerList){
            val position = player.positionOnField
            numberPlayersInPositions[position] = numberPlayersInPositions.getValue(position) + 1
        }
        return numberPlayersInPositions.toMap()
    }

}