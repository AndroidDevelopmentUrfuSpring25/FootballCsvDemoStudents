import chart.ChartResolver
import parser.readCsv
import resolver.Resolver

fun main() {
    val players = readCsv("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)
    println("Игроки без агентства: ${resolver.getCountWithoutAgency()}")
    val (bestDefender, goals) = resolver.getBestScorerDefender()
    println("Защитник с болшим числом голов: $bestDefender, голов: $goals")
    val expensive = resolver.getTheExpensiveGermanPlayerPosition()
    println("Позиция cамого высокооплачиваемого игрока: $expensive")
    val rude = resolver.getTheRudestTeam().name
    println("Самая грубая команда: $rude")

    val chartResolver = ChartResolver("Доля игроков по позициям", players)
    chartResolver.drawChart()
}