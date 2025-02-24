package resolver

import model.Player
import model.Position
import model.Team

class Resolver(val players: List<Player>) : IResolver {

    // Выведите количество игроков, интересы которых не представляет агенство.
    override fun getCountWithoutAgency(): Int =
        players.count { it.agency.isBlank() }


    // Выведите автора наибольшего числа голов из числа защитников и их количество.
    override fun getBestScorerDefender(): Pair<String, Int> =
        players.filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals } ?: error("No defenders found")

    // Выведите русское название позиции самого дорогого немецкого игрока.
    override fun getTheExpensiveGermanPlayerPosition(): String =
        players.filter { it.nationality.equals("Germany", ignoreCase = true) }
            .maxByOrNull { it.transfercost }
            ?.position
            ?.russianName
            ?: error("No players found")


    // Выберите команду с наибольшим числом удалений на одного игрока.
    override fun getTheRudestTeam(): Team =
        players.groupBy { it.team }
            .maxByOrNull { it.value.map { player -> player.redCards }.average() }
            ?.key
            ?: error("No teams found")
}