import parser.PlayerCsvParser
import render.ChartRenderer
import resolver.Resolver
import kotlin.io.path.Path

fun main(args: Array<String>) {
    val pathToData = Path("src")
        .resolve("main")
        .resolve("resources")
        .resolve("fakePlayers.csv")

    val parser = PlayerCsvParser(pathToData)
    val playersData = parser.fetchData()

    val resolver = Resolver(playersData)

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam()}")

    ChartRenderer.visualizeChart(resolver)
}
