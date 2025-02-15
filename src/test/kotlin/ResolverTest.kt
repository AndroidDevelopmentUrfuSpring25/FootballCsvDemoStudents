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
            Team = Team("Team 1", "City 1"),
            Position = "DEFENDER",
            Nationality = "Russia",
            Agency = "Agency 1",
            TransferCost = 1000,
            Participations = 10,
            Goals = 2,
            Assists = 4,
            YellowCards = 2,
            RedCards = 2,
        ),
        Player(
            "Player 2",
            Team = Team("Team 2", "City 2"),
            Position = "DEFENDER",
            Nationality = "Germany",
            Agency = "",
            TransferCost = 1000,
            Participations = 15,
            Goals = 4,
            Assists = 6,
            YellowCards = 3,
            RedCards = 3,
        ),
        Player(
            "Player 3",
            Team = Team("Team 1", "City 1"),
            Position = "FORWARD",
            Nationality = "Germany",
            Agency = "",
            TransferCost = 3000,
            Participations = 15,
            Goals = 4,
            Assists = 6,
            YellowCards = 3,
            RedCards = 1,
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
        val actual = resolver.getTheRudestTeam().Name to resolver.getTheRudestTeam().City
        val expected = "Team 2" to "City 2"

        assertEquals(expected, actual)
    }
}