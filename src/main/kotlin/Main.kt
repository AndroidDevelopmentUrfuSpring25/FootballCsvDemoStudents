import model.Team
import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {
    val players = CsvParser.parsePlayers("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    println("Количество игроков без агентства: ${resolver.getCountWithoutAgency()}")
    println("Лучший бомбардир среди защитников: ${resolver.getBestScorerDefender()}")
    println("Самый дорогой немецкий игрок играет на позиции: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Самая грубая команда: ${resolver.getTheRudestTeam().name}")

    resolver.getTopTeamsByTransferCost("Амелин_Вариант_2.png")
}