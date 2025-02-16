import drawer.ChartDrawer
import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {
    val parser = CsvParser
    val players = parser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)
    //Ќе получилось настроить кодировку консоли
    println("The number of players whose interests are not represented by the agency: ${resolver.getCountWithoutAgency()}")
    println("The defender with the most goals: ${resolver.getBestScorerDefender()}")
    println("The position of the most expensive German player: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("The team with the highest average number of red cards per player: ${resolver.getTheRudestTeam().name}")
    ChartDrawer().drawChart(players)
}