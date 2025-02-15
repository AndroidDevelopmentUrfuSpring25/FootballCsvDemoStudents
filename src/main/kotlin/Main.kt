import model.Player
import model.Team
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.style.Theme
import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {
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
            TransferCost = row["Transfer cost"]!!.toInt(),
            Participations = row["Participations"]!!.toInt(),
            Goals = row["Goals"]!!.toInt(),
            Assists = row["Assists"]!!.toInt(),
            YellowCards = row["Yellow cards"]!!.toInt(),
            RedCards = row["Red cards"]!!.toInt()
        )
    }

    val resolver = Resolver(players)
    println("--- Результаты анализа ---")
    println("Количество игроков без агентства: ${resolver.getCountWithoutAgency()}")
    println("Лучший бомбардир среди защитников: ${resolver.getBestScorerDefender()}")
    println("Самый дорогой немец по позиции: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Самая грубая команда: ${resolver.getTheRudestTeam().Name} (${resolver.getTheRudestTeam().City})")
    println("--------------------------")

    val forwards = players.filter { it.Position == "FORWARD" }

    val forwardsData = mapOf(
        "Goals" to forwards.map { it.Goals }.sorted(),
        "TransferCost" to forwards.map { it.TransferCost }.sorted()
    )

    forwardsData.plot {
        bars {
            x("Goals")
            y("TransferCost")
        }

        layout.title = "Forwards Goals to Transfer Cost"
        layout.theme = Theme.DARCULA
        layout.size = 1000 to 500
    }.save("ForwardsGoalsToTransferCost.png")

    println("График сохранен в lets-plot-images/ForwardsGoalsToTransferCost.png")
}