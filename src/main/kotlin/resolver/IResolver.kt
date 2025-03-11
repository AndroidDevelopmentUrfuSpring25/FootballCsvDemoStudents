package resolver

interface IResolver {
    fun getCountWithoutAgency(): Int
    fun getBestScorerDefender(): Pair<String, Int>
    fun getTheExpensiveGermanPlayerPosition(): String
    fun getTheRudestTeam(): String
    fun calculatePositionDistribution(): Map<String, Double>
}
