package parser

import mapper.PlayerMapper
import model.Player
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

const val HEADER_SIZE = 1L
const val VALID_COLUMNS_SIZE = 12

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
                true
            }
            .map { columns -> PlayerMapper.fetchDto(columns) }
            .collect(Collectors.toList())
    }
}