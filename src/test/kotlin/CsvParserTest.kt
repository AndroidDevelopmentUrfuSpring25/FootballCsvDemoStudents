import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import parser.CsvParser

/**
 * Тест на класс парсера
 */
class CsvParserTest {
    /**
     * Проверка правильного распределения параметров
     */
    @Test
    fun parseTest() {
        val structFootballData = CsvParser.parse("src/main/resources/fakePlayers.csv")
        assertEquals("MIDFIELD", structFootballData[0][NameParameters.POSITION])
        assertEquals("Everette Kovacek MD", structFootballData[2][NameParameters.NAME])
    }
}