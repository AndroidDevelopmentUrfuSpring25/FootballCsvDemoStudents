package resolver
import model.Player
import model.Position
import model.Team
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
class IResolverTest {
    private lateinit var resolver: Resolver
    private lateinit var players: List<Player>

    @BeforeEach
    fun setUp() {
        players = listOf(
            Player("Player1", Team("TeamA", "VA"), Position.DEFENDER, "Germany", "", 100, 10, 5, 2, 1, 0),
            Player("Player2", Team("TeamB","VA"), Position.DEFENDER, "Germany", "", 200, 15, 7, 3, 2, 1),
            Player("Player3", Team("TeamC", "VA"), Position.FORWARD, "Spain", "Agency1", 300, 20, 10, 5, 3, 0),
            Player("Player4", Team("TeamD", "VA"), Position.MIDFIELD, "Brazil", "", 150, 12, 6, 4, 1, 0),
            Player("Player5", Team("TeamE", "VA"), Position.DEFENDER, "Germany", "Agency2", 250, 8, 3, 1, 0, 0)
        )
        resolver = Resolver(players)
    }

    @Test
    fun testGetCountWithoutAgency() {
        val count = resolver.getCountWithoutAgency()
        assertEquals(3, count) // Ожидаем, что 3 игрока без агентства
    }

    @Test
    fun testGetBestScorerDefender() {
        val bestScorer = resolver.getBestScorerDefender()
        assertEquals("Player2", bestScorer.first)
        assertEquals(7, bestScorer.second)
    }

    @Test
    fun testGetTheMostExpensiveGermanPlayerPosition() {
        val position = resolver.getTheMostExpensiveGermanPlayerPosition()
        assertEquals("Защитник", position)
    }

    @Test
    fun testGetTheRudestTeam() {
        val rudestTeam = resolver.getTheRudestTeam()
        assertEquals(Team("TeamB", "VA"), rudestTeam)
    }

    @Test
    fun testGetNationalityShare() {
        val nationalityShare = resolver.getNationalityShare()
        assertEquals(3, nationalityShare.size)
        assertTrue((nationalityShare["Germany"] ?: 0.0) > 0)
        assertTrue((nationalityShare["Spain"] ?: 0.0) > 0)
        assertTrue((nationalityShare["Brazil"] ?: 0.0) > 0)
    }
}