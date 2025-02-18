// Language: Kotlin
package main.kotlin

import model.Player
import parser.CsvParser
import resolver.Resolver
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartFrame
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filePath = "src/main/resources/fakePlayers.csv"
            val players: List<Player> = CsvParser.parsePlayers(filePath)
            val resolver = Resolver(players)
            println("Количество игроков без агенства: ${resolver.getCountWithoutAgency()}")
            val (bestDefender, goals) = resolver.getBestScorerDefender()
            println("Лучший бомбардир среди защитников: $bestDefender, голов: $goals")
            println("Позиция самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
            val rudestTeam = resolver.getTheRudestTeam()
            println("Команда с наивысшим средним числом красных карточек: ${rudestTeam.name} (${rudestTeam.city})")

            val series = XYSeries("Нападающие")
            players.filter { it.position.uppercase() == "FORWARD" }.forEach {
                series.add(it.transferCost, it.goals)
            }
            val dataset = XYSeriesCollection(series)
            val chart = ChartFactory.createScatterPlot(
                "Зависимость голов от трансферной стоимости (нападающие)",
                "Трансферная стоимость",
                "Количество голов",
                dataset
            )
            val frame = ChartFrame("Вариант 3", chart)
            frame.pack()
            frame.isVisible = true
        }
    }
}