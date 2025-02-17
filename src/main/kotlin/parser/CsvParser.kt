package parser

import model.Player
import java.io.File

object CsvParser {
    fun getParsedObjectsList(fileName: String) : List<Player>{
        val list = mutableListOf<Player>()
        File(fileName).bufferedReader().useLines { lines ->
            lines.drop(1).forEach {
                val player = Player.fromValues(it.split(";"))
                list.add(player)
            }
        }
        return list
    }
}