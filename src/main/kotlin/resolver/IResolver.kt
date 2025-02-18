package resolver

import model.Player
import model.Team

interface IResolver {

    fun getCountWithoutAgency(): Int

    fun getBestScorerDefender(): Pair<String, Int>

    fun getTheMostExpensiveGermanPlayerPosition(): String

    fun getTheRudestTeam(): Team

    fun getPositionShare(): Map<String, Double>
}