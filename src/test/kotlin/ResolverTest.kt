import model.Player
import model.Position
import model.Team
import org.junit.jupiter.api.BeforeEach
import resolver.Resolver
import kotlin.test.Test
import kotlin.test.assertEquals

class ResolverTest {
    private lateinit var resolver: Resolver
    private lateinit var players: List<Player>

    @BeforeEach
    fun setUp() {
        players = listOf(
            Player(
                "Player1",
                Team("TeamA", "Germany"),
                Position.DEFENDER,
                "German",
                "",
                750_000,
                100,
                10,
                5,
                3,
                9
            ),
            Player(
                "Player2",
                Team("TeamA", "Germany"),
                Position.FORWARD,
                "German",
                "AgencyA",
                250_000,
                76,
                17,
                2,
                6,
                4
            ),
            Player(
                "Player3",
                Team("TeamB", "Germany"),
                Position.FORWARD,
                "German",
                "AgencyB",
                900_000,
                100,
                10,
                5,
                3,
                9
            ),
        )

        resolver = Resolver(players)
    }

    @Test
    fun testGetCountWithoutAgency() {
        val count = resolver.getCountWithoutAgency()
        assertEquals(1, count)
    }

    @Test
    fun testGetBestScorerDefender() {
        val bestScorer = resolver.getBestScorerDefender()
        assertEquals("Player1", bestScorer.first)
        assertEquals(10, bestScorer.second)
    }

    @Test
    fun testGetTheExpensiveGermanPlayerPosition() {
        val position = resolver.getTheExpensiveGermanPlayerPosition()
        assertEquals(Position.FORWARD.posName, position)
    }

    @Test
    fun testGetTheRudestTeam() {
        val rudestTeam = resolver.getTheRudestTeam()
        assertEquals("TeamA", rudestTeam.name)
    }

    @Test
    fun testTopTeamsByTransferCost() {
        val topTeams = resolver.topTeamsByTransferCost()
        assertEquals(2, topTeams.size)
        assertEquals("TeamA", topTeams[0].first.name)
        assertEquals(1_000_000, topTeams[0].second)
    }
}