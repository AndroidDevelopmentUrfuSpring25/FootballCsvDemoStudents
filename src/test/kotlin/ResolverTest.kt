package resolver

import model.Player
import model.PlayerPosition
import model.Team
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class ResolverTest {

    private val players: List<Player> = listOf(
        Player(
            "Player 1",
            team = Team("Team 1", "City 1"),
            position = "DEFENDER",
            nationality = "Russia",
            agency = "Agency 1",
            transferCost = 1000,
            participations = 10,
            goals = 2,
            assists = 4,
            yellowCards = 2,
            redCards = 2,
        ),
        Player(
            "Player 2",
            team = Team("Team 2", "City 2"),
            position = "DEFENDER",
            nationality = "Germany",
            agency = "",
            transferCost = 1000,
            participations = 15,
            goals = 4,
            assists = 6,
            yellowCards = 3,
            redCards = 3,
        ),
        Player(
            "Player 3",
            team = Team("Team 1", "City 1"),
            position = "FORWARD",
            nationality = "Germany",
            agency = "",
            transferCost = 3000,
            participations = 15,
            goals = 4,
            assists = 6,
            yellowCards = 3,
            redCards = 1,
        )
    )

    private val resolver: IResolver = Resolver(players)

    @Test
    fun testCountWithoutAgency() {
        assertEquals(2, resolver.getCountWithoutAgency())
    }

    @Test
    fun testBestScorerDefender() {
        assertEquals("Player 2" to 4, resolver.getBestScorerDefender())
    }

    @Test
    fun testTheExpensiveGermanPlayerPosition() {
        assertEquals(PlayerPosition.FORWARD.russianName, resolver.getTheExpensiveGermanPlayerPosition())
    }

    @Test
    fun testTheRudestTeam() {
        val actual = resolver.getTheRudestTeam().name to resolver.getTheRudestTeam().city
        val expected = "Team 2" to "City 2"

        assertEquals(expected, actual)
    }
}