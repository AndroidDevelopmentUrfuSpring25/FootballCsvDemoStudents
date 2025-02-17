import model.Player
import model.Position
import model.Team
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import resolver.Resolver
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ResolverTest {

    private lateinit var correctPlayers: List<Player>
    private lateinit var playersWithoutDefendersAndGermanyNationality: List<Player>

    @BeforeAll
    fun setup() {
        correctPlayers = listOf(
            Player(
                "Name1", Team("TeamName1", "City"), Position.FORWARD,
                "Germany", "", 6,
                100, 10, 15, 1, 10
            ),
            Player(
                "Name2", Team("TeamName2", "City"), Position.DEFENDER,
                "Germany", "", 900,
                100, 80, 15, 1, 30
            ),
            Player(
                "Name2", Team("TeamName2", "City"), Position.FORWARD,
                "England", "Agency", 100,
                100, 10, 15, 1, 40
            ),
            Player(
                "Name2", Team("TeamName1", "City"), Position.DEFENDER,
                "Scotland", "Agency", 100,
                100, 10, 15, 1, 10
            ),
            Player(
                "Name2", Team("TeamName3", "City"), Position.DEFENDER,
                "Brazil", "Agency", 100, 100,
                10, 15, 1, 10
            )
        )

        playersWithoutDefendersAndGermanyNationality = listOf(
            Player(
                "Name1", Team("TeamName1", "City"), Position.FORWARD,
                "England", "", 6, 100,
                10, 15, 1, 10
            )
        )
    }

    @Test
    fun getCountWithoutAgencyWithCorrectPlayersTest() {
        val resolver = Resolver(correctPlayers)
        assertEquals(2, resolver.getCountWithoutAgency())
    }

    @Test
    fun getCountWithoutAgencyWithEmptyPlayersTest() {
        val resolver = Resolver(emptyList())
        assertEquals(0, resolver.getCountWithoutAgency())

    }

    @Test
    fun getTheRudestTeamWithCorrectPlayersTest() {
        val resolver = Resolver(correctPlayers)
        assertEquals("TeamName2", resolver.getTheRudestTeam().name)
    }

    @Test
    fun getTheRudestTeamWithEmptyPlayersTest() {
        val resolver = Resolver(emptyList())
        val exception = assertThrows<Exception> {
            resolver.getTheRudestTeam()
        }
        assertEquals("Empty players list", exception.message)
    }

    @Test
    fun getTheExpensiveGermanPlayerPositionWithCorrectPlayersTest() {
        val resolver = Resolver(correctPlayers)
        assertEquals("Защитник", resolver.getTheExpensiveGermanPlayerPosition())
    }

    @Test
    fun getTheExpensiveGermanPlayerPositionEmptyPlayersTest() {
        val resolver = Resolver(emptyList())
        val exception = assertThrows<Exception> {
            resolver.getTheExpensiveGermanPlayerPosition()
        }
        assertEquals("Germany player no founded", exception.message)
    }

    @Test
    fun getTheExpensiveGermanPlayerPositionWithoutGermanyPlayersTest() {
        val resolver = Resolver(playersWithoutDefendersAndGermanyNationality)
        val exception = assertThrows<Exception> {
            resolver.getTheExpensiveGermanPlayerPosition()
        }
        assertEquals("Germany player no founded", exception.message)
    }

    @Test
    fun getBestScorerDefenderWithCorrectPlayersTest() {
        val resolver = Resolver(correctPlayers)
        val pair = resolver.getBestScorerDefender()
        assertEquals("Name2", pair.first)
        assertEquals(80, pair.second)
    }

    @Test
    fun getBestScorerDefenderWithoutDefenderPlayersTest() {
        val resolver = Resolver(playersWithoutDefendersAndGermanyNationality)
        val exception = assertThrows<Exception> {
            resolver.getBestScorerDefender()
        }
        assertEquals("Defenders not founded", exception.message)
    }
}
