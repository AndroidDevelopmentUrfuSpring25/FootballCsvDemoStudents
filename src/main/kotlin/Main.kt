import parser.CsvParser

fun main(args: Array<String>) {
    val footballData = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val footballManager = FootballManager(footballData)

    val playerList = footballManager.getPlayerList()
    val teamList = footballManager.getTeamList()
}