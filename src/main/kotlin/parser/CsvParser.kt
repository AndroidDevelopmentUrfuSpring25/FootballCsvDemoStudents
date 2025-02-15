package parser

import java.io.File

object CsvParser {
    fun parse(fileName: String): List<Map<String, String>> {
        val file = File(fileName)

        val lines = file.readLines().map { line -> line.split(";") }
        val headers = lines[0]

        val parsedObject = lines.subList(1, lines.size).map { line ->
            headers.zip(line).toMap()
        }

        return parsedObject;
    }
}