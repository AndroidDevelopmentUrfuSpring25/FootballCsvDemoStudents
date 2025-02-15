package resolver

import model.Player
import model.PlayerPosition
import model.Team

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count { it.Agency == "" }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestScorer = players
            .filter { it.Position == "DEFENDER" }
            .maxBy { it.Goals }

        return bestScorer.Name to bestScorer.Goals
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensiveGerman = players
            .filter { it.Nationality == "Germany" }
            .maxBy { it.TransferCost }

        return PlayerPosition.valueOf(expensiveGerman.Position).russianName
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.Team }.maxBy { it -> it.value.sumOf { it.RedCards } }.key
    }

}