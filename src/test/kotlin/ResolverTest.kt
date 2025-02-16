import model.Player
import model.Position
import model.Team
import org.junit.jupiter.api.assertThrows
import resolver.Resolver
import kotlin.test.Test
import kotlin.test.assertEquals

class ResolverTest {
    private val defaultPlayers = listOf(
        Player(
            "Котёнок 1",
            Team("Котята", "Котьск"),
            Position.DEFENDER,
            "Кот",
            "",
            37_000,
            37,
            30,
            70,
            3,
            7
        ),
        Player(
            "Котёнок 2",
            Team("Котята", "Котьск"),
            Position.FORWARD,
            "Germany",
            "CatAg",
            42_000,
            42,
            40,
            20,
            4,
            2
        ),
        Player(
            "Щеночек 1",
            Team("Щенята", "Догск"),
            Position.FORWARD,
            "Собака",
            "DogAg",
            69_000,
            69,
            60,
            90,
            6,
            9
        )
    )

    @Test
    fun testGetCountWithoutAgency() {
        // Given
        val resolver = Resolver(defaultPlayers)
        // When
        val count = resolver.getCountWithoutAgency()
        // Then
        assertEquals(1, count)
    }

    @Test
    fun testGetCountWithoutAgencyWithNoPlayers() {
        // Given
        val resolver = Resolver(emptyList())
        // When
        val count = resolver.getCountWithoutAgency()
        // Then
        assertEquals(0, count)
    }

    @Test
    fun testGetCountWithoutAgencyWithAgenciesOnly() {
        // Given
        val resolver = Resolver(listOf(defaultPlayers[1], defaultPlayers[2]))
        // When
        val count = resolver.getCountWithoutAgency()
        // Then
        assertEquals(0, count)
    }

    @Test
    fun testGetBestScorerDefender() {
        // Given
        val resolver = Resolver(defaultPlayers)
        // When
        val bestScorer = resolver.getBestScorerDefender()
        // Then
        assertEquals("Котёнок 1", bestScorer.first)
        assertEquals(30, bestScorer.second)
    }

    @Test
    fun testGetBestScorerDefenderWithNoPlayers() {
        // Given
        val resolver = Resolver(emptyList())
        // When
        val call = { resolver.getBestScorerDefender() }
        // Then
        assertThrows<Exception> { call() }
    }

    @Test
    fun testGetBestScorerDefenderWithNoDefenders() {
        // Given
        val resolver = Resolver(listOf(defaultPlayers[1], defaultPlayers[2]))
        // When
        val call = { resolver.getBestScorerDefender() }
        // Then
        assertThrows<Exception> { call() }
    }

    @Test
    fun testGetTheExpensiveGermanPlayerPosition() {
        // Given
        val resolver = Resolver(defaultPlayers)
        // When
        val position = resolver.getTheExpensiveGermanPlayerPosition()
        // Then
        assertEquals(Position.FORWARD.russianName, position)
    }

    @Test
    fun testGetTheExpensiveGermanPlayerPositionWithNoPlayers() {
        // Given
        val resolver = Resolver(emptyList())
        // When
        val call = { resolver.getTheExpensiveGermanPlayerPosition() }
        // Then
        assertThrows<Exception> { call() }
    }

    @Test
    fun testGetTheExpensiveGermanPlayerPositionWithNoGermans() {
        // Given
        val resolver = Resolver(listOf(defaultPlayers[0], defaultPlayers[2]))
        // When
        val call = { resolver.getTheExpensiveGermanPlayerPosition() }
        // Then
        assertThrows<Exception> { call() }
    }

    @Test
    fun testGetTheRudestTeam() {
        // Given
        val resolver = Resolver(defaultPlayers)
        // When
        val rudestTeam = resolver.getTheRudestTeam()
        // Then
        assertEquals("Щенята", rudestTeam.name)
    }

    @Test
    fun testGetTheRudestTeamWithNoPlayers() {
        // Given
        val resolver = Resolver(emptyList())
        // When
        val call = { resolver.getTheRudestTeam() }
        // Then
        assertThrows<Exception> { call() }
    }
}