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
            val dataLinesToList = mutableListOf<List<String>>()
            file.forEachLine { line ->
                val columnsValue = line.split(";")
                dataLinesToList.add(columnsValue)
            }

            val structuredData = mutableListOf<Map<NameParameters, String>>()
            val columns = dataLinesToList[0].map { title ->
                NameParameters.entries.find { it.title == title }
            }
            for (i in 1..<dataLinesToList.size) {
                structuredData.add(structure(columns, dataLinesToList[i]))
            }

            return structuredData.toList()

        } else {
            throw FileNotFoundException("Файл $filePath не найден")
        }
    }

    /**
     * Структурирует данные в словарь
     */
    private fun structure(columns: List<NameParameters?>, values: List<String>): Map<NameParameters, String> {
        val columnWithValue = mutableMapOf<NameParameters, String>()
        for (i in columns.indices) {
            val param = columns[i]
            param ?: throw Exception("Не удалось преобразовать параметр")
            columnWithValue.put(param, values[i])
        }
        return columnWithValue.toMap()
    }
}