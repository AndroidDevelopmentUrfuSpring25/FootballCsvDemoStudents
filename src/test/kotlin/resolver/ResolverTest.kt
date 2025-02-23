package resolver

import dto.player.Position
import model.Player
import model.Team
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.stream.Collectors
import java.util.stream.IntStream

class ResolverTest {

    @Test
    fun getCountWithoutAgency_shouldReturnPlayersWithoutAgencyCount_whenAllDataIsValid() {
        val resolver = Resolver(
            listOf(
                createPlayer(hasAgency = false),
                createPlayer(hasAgency = true),
                createPlayer(hasAgency = false)
            )
        )

        assertEquals(2, resolver.getCountWithoutAgency())
    }

    @Test
    fun getBestScorerDefender_shouldReturnDefenderWithMaxGoals_whenAllDataIsValid() {
        val bestDefender = createPlayer(position = Position.DEFENDER, goals = 100)
        val resolver = Resolver(
            listOf(
                bestDefender,
                createPlayer(position = Position.MIDFIELD, goals = 1000),
                createPlayer(position = Position.DEFENDER, goals = 20)
            )
        )

        assertEquals(Pair(bestDefender.name, bestDefender.goals), resolver.getBestScorerDefender())
    }

    @Test
    fun getTheExpensiveGermanPlayerPosition_shouldReturnMostExpensivePlayersPosition() {
        val resolver = Resolver(
            listOf(
                createPlayer(position = Position.GOALKEEPER, isGerman = true, transferCost = 20L),
                createPlayer(position = Position.MIDFIELD, isGerman = true, transferCost = 50L),
                createPlayer(position = Position.FORWARD, isGerman = false, transferCost = 100L)
            )
        )

        assertEquals(Position.MIDFIELD.name, resolver.getTheExpensiveGermanPlayerPosition())
    }

    @Test
    fun getTheRudestTeam_shouldReturnTeamWithMaxRedCards_whenAllDataIsValid() {
        val team1 = "t1"
        val team2 = "t2"

        val resolver = Resolver(
            listOf(
                createPlayer(teamName = team1, redCardsCount = 2),
                createPlayer(teamName = team2, redCardsCount = 3),
                createPlayer(teamName = team1, redCardsCount = 2),
            )
        )

        assertEquals(team2, resolver.getTheRudestTeam().name)
    }

    @Test
    fun getTenMostExpensiveTeams_shouldReturnUpTo10MostExpensiveTeams_whenAllDataIsValidAndTeamsLessThen10() {
        val resolver = Resolver(
            listOf(
                createPlayer(teamName = "1", transferCost = 1L),
                createPlayer(teamName = "2", transferCost = 1L),
                createPlayer(teamName = "3", transferCost = 3L),
                createPlayer(teamName = "1", transferCost = 1L),
                createPlayer(teamName = "1", transferCost = 2L)
            )
        )

        val mostExpensiveTeams = resolver.getTenMostExpensiveTeams()
        assertEquals(3, mostExpensiveTeams.size)

        assertEquals("1", mostExpensiveTeams[0].first.name)
        assertEquals("3", mostExpensiveTeams[1].first.name)
        assertEquals("2", mostExpensiveTeams[2].first.name)
    }

    @Test
    fun getTenMostExpensiveTeams_shouldReturn10SortedDescendingExpensiveTeams_whenAllDataIsValidAndTeamsMoreThen10() {
        val resolver = Resolver(
            IntStream.rangeClosed(0, 20)
                .mapToObj { i -> createPlayer(teamName = i.toString(), transferCost = i.toLong()) }
                .collect(Collectors.toList())
        )

        val mostExpensiveTeams = resolver.getTenMostExpensiveTeams()

        assertEquals(10, mostExpensiveTeams.size)

        assertEquals("20", mostExpensiveTeams[0].first.name)
        assertEquals("11", mostExpensiveTeams[9].first.name)
    }

    private fun createPlayer(
        teamName: String = RandomStringUtils.randomAlphabetic(5),
        position: Position = Position.DEFENDER,
        hasAgency: Boolean = true,
        isGerman: Boolean = false,
        transferCost: Long = RandomUtils.nextLong(),
        goals: Int = RandomUtils.nextInt(),
        redCardsCount: Int = RandomUtils.nextInt(),
    ): Player {
        return Player(
            RandomStringUtils.randomAlphabetic(5),
            Team(teamName, "city"),
            position,
            if (isGerman) "Germany" else RandomStringUtils.randomAlphabetic(5),
            if (hasAgency) RandomStringUtils.randomAlphabetic(5) else null,
            transferCost,
            RandomUtils.nextInt(),
            goals,
            RandomUtils.nextInt(),
            RandomUtils.nextInt(),
            redCardsCount
        )
    }
}