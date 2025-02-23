package resolver

import dto.player.Position
import model.Player
import model.Team
import org.apache.commons.lang3.StringUtils
import java.util.stream.Collectors

class Resolver(
    private val playersData: List<Player>
) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return playersData.count { player -> StringUtils.isBlank(player.agency) }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestScorer = playersData.filter { player -> player.position == Position.DEFENDER }
            .maxBy { player -> player.goals }

        return Pair(bestScorer.name, bestScorer.goals)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensiveGermanPlayer = playersData.filter { player -> player.nationality == "Germany" }
            .maxBy { player -> player.transferCost }

        return expensiveGermanPlayer.position.name
    }

    override fun getTheRudestTeam(): Team {
        val redCardsCountByTeam = playersData.stream().collect(
            Collectors.groupingBy(
                { player -> player.team },
                Collectors.mapping(
                    { player -> player.redCardsCount },
                    Collectors.counting()
                )
            )
        )

        return redCardsCountByTeam.maxBy { entry -> entry.value }.key
    }

    fun getTenMostExpensiveTeams(): List<Pair<Team, Long>> {
        val teamsTransferCost = playersData.stream().collect(
            Collectors.groupingBy(
                { player -> player.team },
                Collectors.summingLong { player -> player.transferCost }
            )
        )

        return teamsTransferCost.entries
            .sortedByDescending { entry -> entry.value }
            .map { entry -> Pair(entry.key, entry.value) }
            .take(10)
    }
}