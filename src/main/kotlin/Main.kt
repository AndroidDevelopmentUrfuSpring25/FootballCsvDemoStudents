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
            name = row["Name"]!!,
            team = Team(
                name = row["Team"]!!,
                city = row["City"]!!
            ),
            position = row["Position"]!!,
            nationality = row["Nationality"]!!,
            agency = row["Agency"]!!,
            transferCost = row["Transfer cost"]!!.toInt(),
            participations = row["Participations"]!!.toInt(),
            goals = row["Goals"]!!.toInt(),
            assists = row["Assists"]!!.toInt(),
            yellowCards = row["Yellow cards"]!!.toInt(),
            redCards = row["Red cards"]!!.toInt()
        )
    }

    val resolver = Resolver(players)
    println("--- Результаты анализа ---")
    println("Количество игроков без агентства: ${resolver.getCountWithoutAgency()}")
    println("Лучший бомбардир среди защитников: ${resolver.getBestScorerDefender()}")
    println("Самый дорогой немец по позиции: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Самая грубая команда: ${resolver.getTheRudestTeam().name} (${resolver.getTheRudestTeam().city})")
    println("--------------------------")

    val forwards = players.filter { it.position == "FORWARD" }

    val forwardsData = mapOf(
        "Goals" to forwards.map { it.goals }.sorted(),
        "TransferCost" to forwards.map { it.transferCost }.sorted()
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