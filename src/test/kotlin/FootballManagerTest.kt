import org.junit.jupiter.api.Test
import parser.CsvParser
import kotlin.test.assertEquals

/**
 * Тест на класс менеджера
 */
class FootballManagerTest {
    /**
     * Проверяет созданный экземпляр класса команды
     */
    @Test
    fun OneTeamTest(){
        val footballData = CsvParser.parse("src/main/resources/fakePlayers.csv")
        val footballManager = FootballManager(footballData)

        val teamList = footballManager.getTeamList()
        val teamNevada = teamList[0]
        assertEquals("Nevada whales", teamNevada.name)
        assertEquals(32, teamNevada.getPlayers().size)
    }
}