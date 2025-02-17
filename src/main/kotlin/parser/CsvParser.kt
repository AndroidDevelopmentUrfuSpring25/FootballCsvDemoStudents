package parser

import java.io.File
import java.io.FileNotFoundException

/**
 * Парсит данные из csv файла
 */
object CsvParser{

    /**
     * Парсит данные из файла
     * @return список словарей, где key - категория
     */
    fun parse(filePath: String):List<Map<String, String>>{
        val file = File(filePath)
        if (file.exists()) {
            val dataLinesToList = mutableListOf<List<String>>()
            file.forEachLine { line ->
                val columnsValue = line.split(";")
                dataLinesToList.add(columnsValue)
            }

            val structuredData = mutableListOf<Map<String, String>>()
            val columns = dataLinesToList[0]
            for(i in 1..<dataLinesToList.size){
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
    private fun structure(columns:List<String>, values: List<String>):Map<String, String>{
        val columnWithValue = mutableMapOf<String, String>()
        for(i in columns.indices){
            columnWithValue.put(columns[i], values[i])
        }
        return columnWithValue.toMap()
    }
}