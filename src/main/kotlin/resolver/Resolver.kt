package resolver



import model.*

class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position == "DEFENDER" }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals } ?: ("" to 0)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferValue }
            ?.position ?: "123"
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
            .maxByOrNull { (_, teamPlayers) -> teamPlayers.sumOf { it.redCards }.toDouble() / teamPlayers.size }
            ?.key ?: Team("", "")
    }
}
