import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import parser.CsvParser

class CsvParserTest {
    @Test
    fun parseTest() {
        val structFootballData = CsvParser.parse("src/main/resources/fakePlayers.csv")
        assertEquals("MIDFIELD", structFootballData[0]["Position"])
        assertEquals("Everette Kovacek MD", structFootballData[2]["Name"])
    }
}