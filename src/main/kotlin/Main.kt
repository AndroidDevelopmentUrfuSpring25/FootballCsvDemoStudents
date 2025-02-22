import drawer.GraphDrawer
import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    val countPlayersWithoutAgency = resolver.getCountWithoutAgency()
    val bestDefender = resolver.getBestScorerDefender()
    val mostExpensiveGermanPlayerPosition = resolver.getTheExpensiveGermanPlayerPosition()
    val teamWithRedCards = resolver.getTheRudestTeam()

    println("Количество игроков, интересы которых не представляет агенство: $countPlayersWithoutAgency")
    println("Автор наибольшего числа голов из числа защитников и их количество: Имя - ${bestDefender.first}, Кол-во голов - ${bestDefender.second}")
    println("Русское название позиции самого дорогого немецкого игрока: $mostExpensiveGermanPlayerPosition")
    println("Команда с наибольшим средним числом красных карточек на одного игрока: ${teamWithRedCards.name} (${teamWithRedCards.city})")

    val graphDrawer = GraphDrawer()
    val forwards = resolver.getForwards()

    graphDrawer.drawGoalsCostDependencyForForwards(forwards)
}