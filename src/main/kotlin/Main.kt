import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {
    val footballData = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val footballManager = FootballManager(footballData)

    val playerList = footballManager.getPlayerList()
    val teamList = footballManager.getTeamList()

    val resolver = Resolver(playerList, teamList)

    println(resolver.getCountWithoutAgency())
    println(resolver.getBestScorerDefender())
    println(resolver.getTheExpensiveGermanPlayerPosition())
    println(resolver.getTheRudestTeam().getDescription())

    GraphPlotter.createPieChart(resolver.getDistributionPositions())
}