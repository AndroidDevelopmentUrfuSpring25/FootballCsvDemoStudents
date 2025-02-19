package resolver

import model.Player

class Resolver : IResolver {
    override fun countPlayersWithoutAgency(players: List<Player>): Int {
        return players.count { it.agency == null }
    }

    override fun topScoringDefender(players: List<Player>): Pair<String, Int>? {
        return players.filter { it.position.lowercase().contains("def") }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals }
    }


    override fun mostExpensiveGermanPlayerPosition(players: List<Player>): String? {
        val germanPlayer = players.filter { it.nationality.lowercase().contains("ger") }
            .maxByOrNull { it.transferValue }

        val position = germanPlayer?.position

        return when (position?.lowercase()) {
            "forward" -> "Нападающий"
            "midfielder" -> "Полузащитник"
            "defender" -> "Защитник"
            "goalkeeper" -> "Вратарь"
            else -> "Неизвестная позиция"
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
    override fun top10TeamsByTransferValue(players: List<Player>): List<Pair<String, Double>> {
        return players.groupBy { it.team }
            .mapValues { (_, teamPlayers) -> teamPlayers.sumOf { it.transferValue } }
            .toList()
            .sortedByDescending { it.second }
            .take(10)
    }

}


