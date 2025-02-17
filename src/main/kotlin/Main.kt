import parser.CsvParser
import resolver.PlayerStatisticsResolver
import model.Player
import model.Team

fun main() {
    val input = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val players = input.map { row ->
        Player(
            Name = row["Name"]!!,
            Team = Team(
                Name = row["Team"]!!,
                City = row["City"]!!
            ),
            Position = row["Position"]!!,
            Nationality = row["Nationality"]!!,
            Agency = row["Agency"]!!,
            TransferCost = row["Transfer cost"]!!.toDouble(),
            Participations = row["Participations"]!!.toInt(),
            Goals = row["Goals"]!!.toInt(),
            Assists = row["Assists"]!!.toInt(),
            YellowCards = row["Yellow cards"]!!.toInt(),
            RedCards = row["Red cards"]!!.toInt()
        )
    }
    val resolver = PlayerStatisticsResolver(players)

    println("Количество игроков без агенства: ${resolver.getCountWithoutAgency()}")
    val bestDefender = resolver.getBestScorerDefender()
    println("\nЛучший защитник: ${bestDefender.first}, ${bestDefender.second} голов")
    println("\nПозиция самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("\nКоманда с наибольшим количеством красных карточек: ${resolver.getTheRudestTeam().Name}")
}