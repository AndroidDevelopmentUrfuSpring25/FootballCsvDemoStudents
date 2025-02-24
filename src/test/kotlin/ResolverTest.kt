package resolver

import model.Player
import model.Position
import model.Team
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ResolverTest {

    private val teamA = Team("Team A", "City A")
    private val teamB = Team("Team B", "City B")
    private val teamC = Team("Team C", "City C")

    private val players = listOf(
        Player(
            name = "Player One",
            team = teamA,
            position = Position.DEFENDER,
            nationality = "Germany",
            agency = "",
            transfercost = 50,
            participations = 10,
            goals = 2,
            assists = 1,
            yellowCards = 3,
            redCards = 1
        ),
        // Защитник с самым большим кол-вом голов
        Player(
            name = "Player Two",
            team = teamA,
            position = Position.DEFENDER,
            nationality = "Spain",
            agency = "AgencyX",
            transfercost = 30,
            participations = 12,
            goals = 5,
            assists = 2,
            yellowCards = 2,
            redCards = 0
        ),
        // Немецкий игрок с самой дорогой стоимостью
        Player(
            name = "Player Three",
            team = teamB,
            position = Position.FORWARD,
            nationality = "Germany",
            agency = "AgencyY",
            transfercost = 100,
            participations = 15,
            goals = 20,
            assists = 5,
            yellowCards = 1,
            redCards = 0
        ),
        Player(
            name = "Player Four",
            team = teamB,
            position = Position.MIDFIELD,
            nationality = "France",
            agency = "AgencyZ",
            transfercost = 40,
            participations = 20,
            goals = 4,
            assists = 4,
            yellowCards = 3,
            redCards = 2
        ),
        Player(
            name = "Player Five",
            team = teamC,
            position = Position.FORWARD,
            nationality = "Italy",
            agency = "AgencyX",
            transfercost = 60,
            participations = 10,
            goals = 1,
            assists = 0,
            yellowCards = 2,
            redCards = 4
        ),
        Player(
            name = "Player Six",
            team = teamC,
            position = Position.DEFENDER,
            nationality = "Brazil",
            agency = "AgencyC",
            transfercost = 20,
            participations = 8,
            goals = 1,
            assists = 1,
            yellowCards = 1,
            redCards = 0
        )
    )

    private val resolver = Resolver(players)

    @Test
    fun testGetCountWithoutAgency() {
        val count = resolver.getCountWithoutAgency()
        assertEquals(1, count)
    }

    @Test
    fun testGetBestScorerDefender() {
        val bestDefender = resolver.getBestScorerDefender()
        assertEquals("Player Two", bestDefender.first)
        assertEquals(5, bestDefender.second)
    }

    @Test
    fun testGetTheExpensiveGermanPlayerPosition() {
        val position = resolver.getTheExpensiveGermanPlayerPosition()
        assertEquals("Нападающий", position)
    }

    @Test
    fun testGetTheRudestTeam() {
        val rudestTeam = resolver.getTheRudestTeam()
        assertEquals(teamC, rudestTeam)
    }
}