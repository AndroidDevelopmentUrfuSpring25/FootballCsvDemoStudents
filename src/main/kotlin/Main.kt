import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {
    val footballData = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val footballManager = FootballManager(footballData)

    val playerList = footballManager.getPlayerList()
    val teamList = footballManager.getTeamList()

    val resolver = Resolver(playerList, teamList)

    println("Количество игроков, интересы которых не представляет агенство:\n" + resolver.getCountWithoutAgency())
    println("Автор наибольшего числа голов из числа защитников и их количество:\n" + resolver.getBestScorerDefender())
    println("Русское название позиции самого дорогого немецкого игрока:\n" + resolver.getTheExpensiveGermanPlayerPosition())
    println("Команда с наибольшим средним числом красных карточек на одного игрока:\n" + resolver.getTheRudestTeam().getDescription())

    GraphPlotter.createPieChart(resolver.getDistributionPositions())
}