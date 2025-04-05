import parser.CsvParser
import plots.PlotBuilder
import resolver.Resolver

fun main() {
    val players = CsvParser.getPlayers("src/main/resources/fakePlayers.csv")

    println("--- Результаты анализа ---")
    Resolver(players).let {
        println("Количество игроков без агентства: ${it.getCountWithoutAgency()}")
        println("Лучший бомбардир среди защитников: ${it.getBestScorerDefender()}")
        println("Самый дорогой немец по позиции: ${it.getTheExpensiveGermanPlayerPosition()}")
        println("Самая грубая команда: ${it.getTheRudestTeam().name} (${it.getTheRudestTeam().city})")
    }
    println("--------------------------")

    PlotBuilder.buildForwardsGoalsToTransferCoastPlot(players)
}