import parser.CsvParser
import resolver.Resolver
import visaualization.Visualization

fun main(args: Array<String>) {
    val players = CsvParser.parseCsv()

    val resolver = Resolver(players)
    println(resolver.getCountWithoutAgency())
    println(resolver.getBestScorerDefender())
    println(resolver.getTheExpensiveGermanPlayerPosition())
    println(resolver.getTheRudestTeam())


    val visualization = Visualization(players)
    val chart = visualization.makeVisualization()
    visualization.showChart(chart)
}