package resolver

import model.Player

class Resolver : IResolver {

    override fun countPlayersWithoutAgency(players: List<Player>): Int {
        return players.count { it.agency.isNullOrEmpty() }
    }

    override fun topScoringDefender(players: List<Player>): Pair<String, Int>? {
        return players.filter { it.position.lowercase().contains("def") }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals }
    }

    override fun mostExpensiveGermanPlayerPosition(players: List<Player>): String? {
        return players.filter { it.nationality.lowercase().contains("ger") }
            .maxByOrNull { it.transferValue }
            ?.let { mostExpensive ->
                when (mostExpensive.position.uppercase()) {
                    "FORWARD" -> "Нападающий"
                    "MIDFIELD" -> "Полузащитник"
                    "DEFENDER" -> "Защитник"
                    "GOALKEEPER" -> "Вратарь"
                    else -> "Неизвестная позиция"
                }
            }
    }

    override fun teamWithMostRedCards(players: List<Player>): String? {
        return players.groupBy { it.team }
            .mapValues { (_, teamPlayers) ->
                teamPlayers.sumOf { it.redCards } / teamPlayers.size.toDouble()
            }
            .maxByOrNull { it.value }
            ?.key
    }

    fun calculatePositionDistribution(players: List<Player>): Map<String, Double> {
        val totalPlayers = players.size
        return players.groupingBy { it.position }
            .eachCount()
            .mapValues { (_, count) -> (count.toDouble() / totalPlayers) * 100 }
    }
}
