import parser.ChartCreator
import parser.CsvParser
import resolver.Resolver

fun main() {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    val bestDefender = resolver.getBestScorerDefender()
    println("Автор наибольшего числа голов из числа защитников: ${bestDefender.first}, количество голов: ${bestDefender.second}")
    println("Самый дорогой немецкий игрок играет на позиции: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam().name}")

    val nationalityDistribution = CsvParser.getNationalityDistribution(players)

    ChartCreator.createPieChart(nationalityDistribution, "src/main/resources/nationality_chart.png")

}