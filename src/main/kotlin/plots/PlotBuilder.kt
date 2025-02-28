package plots

import model.Player
import model.PlayerPosition
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.style.Theme

object PlotBuilder {
    fun buildForwardsGoalsToTransferCoastPlot(players: List<Player>) {
        val forwards = players.filter { it.position == PlayerPosition.FORWARD }

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
}