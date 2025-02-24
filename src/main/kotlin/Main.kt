import drawer.Drawer
import parser.CsvParser
import resolver.Resolver



fun main() {
    val players = CsvParser.readCsv("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)
    println("Игроков без агентства: ${resolver.getCountWithoutAgency()}")
    resolver.getBestScorerDefender().let { (name, goals) ->
        println("Лучший защитник: $name, голов: $goals")
    }
    println("Роль самого высокооплачиваемого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Самая грубая команда: ${resolver.getTheRudestTeam().name}")
    val topTeams = resolver.topTen()
    val drawer = Drawer()
    drawer.drawChart(topTeams)
}



