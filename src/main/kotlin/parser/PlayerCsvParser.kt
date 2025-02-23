package parser

import mapper.PlayerMapper
import model.Player
import org.apache.commons.lang3.StringUtils
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors
import java.util.stream.IntStream

const val HEADER_SIZE = 1L
const val VALID_COLUMNS_SIZE = 12
const val AGENCY_COLUMN_INDEX = 5

class PlayerCsvParser(
    private val filePath: Path,
    private val separator: Char = ';'
) {

    fun fetchData(): List<Player> {
        return Files.lines(filePath)
            .skip(HEADER_SIZE)
            .map { line -> line.split(separator) }
            .filter { columns ->
                if (columns.size != VALID_COLUMNS_SIZE) {
                    println("Line $columns count must be equals $VALID_COLUMNS_SIZE")
                    false
                }
                if (containsInvalidColumn(columns)) {
                    println("Line $columns contains an empty column")
                    false
                }
                true
            }
            .map { columns -> PlayerMapper.fetchDto(columns) }
            .collect(Collectors.toList())
    }

    private fun containsInvalidColumn(columns: List<String>): Boolean {
        return IntStream.range(0, columns.size)
            .anyMatch { i -> i != AGENCY_COLUMN_INDEX && StringUtils.isBlank(columns[i]) }
    }
}