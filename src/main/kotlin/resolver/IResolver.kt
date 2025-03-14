package resolver

import model.Team

interface IResolver {


    fun getCountWithoutAgency(): Int


    fun getBestScorerDefender(): Pair<String, Int>


    fun getTheExpensiveGermanPlayerPosition(): String


    fun getTheRudestTeam(): Team

    fun top10TeamsByTransferValue(): List<Pair<String, Double>>

}
