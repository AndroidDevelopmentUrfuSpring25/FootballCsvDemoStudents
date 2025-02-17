import drawer.Drawer
import resolver.Resolver

fun main(args: Array<String>) {
    val parser = CsvParser
    val players = parser.parse("src/main/resources/fakePlayers.csv")

    val resolver = Resolver(players)

    // Вызов методов и вывод результатов
    println("Count without Agency: ${resolver.getCountWithoutAgency()}")
    val bestScorerDefender = resolver.getBestScorerDefender()
    println("Best Scorer Defender: ${bestScorerDefender.first} with ${bestScorerDefender.second} goals")
    println("Most Expensive German Player Position: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    val rudestTeam = resolver.getTheRudestTeam()
    println("The Rudest Team: ${rudestTeam.name}")

    val drawer = Drawer()
    drawer.drawGoalsVsTransferValueForForwards(players)
}