package parser

import NameParameters
import java.io.File
import java.io.FileNotFoundException

/**
 * Парсит данные из csv файла
 */
object CsvParser {

    /**
     * Парсит данные из файла
     * @return список словарей, где key - категория
     */
    fun parse(filePath: String): List<Map<NameParameters, String>> {
        val file = File(filePath)
        if (file.exists()) {
            val dataLinesToList: List<List<String>> = file.readLines().map { it.split(";") }

            val columns = dataLinesToList[0].map { title ->
                NameParameters.entries.find { it.title == title }!!
            }
            val structuredData = dataLinesToList.drop(1).map { columns.zip(it).toMap() }
            return structuredData.toList()

        } else {
            throw FileNotFoundException("Файл $filePath не найден")
        }
    }
}