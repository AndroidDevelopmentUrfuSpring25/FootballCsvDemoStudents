package resolver

import model.Player
import model.Position
import model.Team

class Resolver (private val players: List<Player>) : IResolver {

    // Выведите количество игроков, интересы которых не представляет агентство.
    override fun getCountWithoutAgency(): Int = players.count { it.agency.isEmpty() }

    // Выведите автора наибольшего числа голов из числа защитников и их количество.
    override fun getBestScorerDefender(): Pair<String, Int> =
        players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals }
            ?: throw Exception("no defenders")

    // Выведите русское название позиции самого дорогого немецкого игрока.
    override fun getTheExpensiveGermanPlayerPosition(): String =
        players
            .filter { it.nationality == "Germany" }
            .maxBy { it.transferCost }
            .let { it.position.russianName }

    // Выберите команду с наибольшим числом удалений на одного игрока.
    override fun getTheRudestTeam(): Team =
        players
            .groupBy { it.team }
            .maxBy { it.value.map { it.redCards }.average() }
            .key

}