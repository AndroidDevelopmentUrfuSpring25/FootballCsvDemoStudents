package resolver

import model.Player
import model.Position
import model.Team
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ResolverTests {
    private lateinit var resolver: Resolver
    private lateinit var players: List<Player>

    @BeforeEach
    fun setUp() {
        players = listOf(
            Player(
                "MID1",
                Team("Team1", "BER"),
                Position.MIDFIELD,
                "Germany",
                "",
                1000000,
                10,
                5,
                2,
                1,
                2
            ),
            Player(
                "DEF1",
                Team("Team2", "BR"),
                Position.DEFENDER,
                "Brazil",
                "",
                1200000,
                10,
                6,
                3,
                2,
                3
            ),
            Player(
                "DEF2",
                Team("Team3", "BRA"),
                Position.DEFENDER,
                "Brazil",
                "",
                1300000,
                13,
                14,
                22,
                3,
                5
            ),
            Player(
                "DEF3",
                Team("Team1", "BER"),
                Position.DEFENDER,
                "Germany",
                "AGENCYGER",
                1650000,
                12,
                31,
                44,
                21,
                4
            ),
            Player(
                "GOALKEEPER1",
                Team("Team1", "BER"),
                Position.GOALKEEPER,
                "Germany",
                "AGENCYGER",
                1900000,
                13,
                0,
                0,
                1,
                4
            ),
            Player(
                "FORWARD1",
                Team("Team2", "BR"),
                Position.FORWARD,
                "Brazil",
                "AGENCYBR",
                1320000,
                24,
                24,
                33,
                3,
                2
            ),
            Player(
                "FORWARD2",
                Team("Team3", "BRA"),
                Position.FORWARD,
                "Brazil",
                "AGENCYBRA",
                1450000,
                11,
                32,
                45,
                6,
                7
            ),
        )
        resolver = Resolver(players)
    }

    @Test
    fun testGetCountWithoutAgency() {
        val count = resolver.getCountWithoutAgency()
        assertEquals(3, count)
    }

    @Test
    fun testGetBestScorerDefender() {
        val bestScorerDefender = resolver.getBestScorerDefender()
        assertEquals("DEF3", bestScorerDefender.first)
        assertEquals(31, bestScorerDefender.second)
    }

    @Test
    fun testGetTheExpensiveGermanPlayerPosition() {
        val mostExpensiveGermanPlayerPosition = resolver.getTheExpensiveGermanPlayerPosition()
        assertEquals(Position.GOALKEEPER.russianName, mostExpensiveGermanPlayerPosition)
    }

    @Test
    fun testGetTheRudestTeam() {
        val rudestTeam = resolver.getTheRudestTeam()
        assertEquals("Team3", rudestTeam.name)
        assertEquals("BRA", rudestTeam.city)
    }

    @Test
    fun testGetForwards() {
        val forwards = resolver.getForwards()
        val expectedForwards = listOf(players[5], players[6])
        assertEquals(expectedForwards, forwards)
    }
}