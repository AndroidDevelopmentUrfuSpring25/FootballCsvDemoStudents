package resolver

import model.Player
import model.Team

class PlayerStatisticsResolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count { it.Agency == "" }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.Position == "DEFENDER" }
            .maxByOrNull { it.Goals }
            ?.let { Pair(it.Name, it.Goals) }
            ?: Pair("Нет защитников", 0)
    }

    enum class PlayerPosition(val russianName: String) {
        MIDFIELD("Центровой"),
        DEFENDER("Защитник"),
        FORWARD("Нападающий"),
        GOALKEEPER("Вратарь")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.Nationality == "Germany" }
            .maxByOrNull { it.TransferCost }
            ?.let { PlayerPosition.valueOf(it.Position).russianName }
            ?: "Нападающий"
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.Team }
            .maxByOrNull { it.value.sumOf { it.RedCards } }
            ?.key
            ?: Team("", "")
    }
}