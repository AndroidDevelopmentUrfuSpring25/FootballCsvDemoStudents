import resolver.Resolver
import drawer.DataDrawer

fun main(args: Array<String>) {
    val resolver = Resolver()
    val countAgencyLessPlayers = resolver.getCountWithoutAgency()
    val bestDefender = resolver.getBestScorerDefender()
    val mostExpensiveGermanPlayer = resolver.getTheMostExpensiveGermanPlayerPosition()
    val rudestTeam = resolver.getTheRudestTeam()
    var positionShare = resolver.getPositionShare()

    println("1. Не представляют агенство. - $countAgencyLessPlayers")
    println("2. Наибольшее число голов - ${bestDefender.first} из числа защитников и их количество. - ${bestDefender.second}")
    println("3. Русское название позиции самого дорогого немецкого игрока - $mostExpensiveGermanPlayer")
    println("4. Команда с наибольшим средним числом красных карточек на одного игрока. - ${rudestTeam.name}")

    val dataDrawer = DataDrawer();
    dataDrawer.drawPositionShareChart(positionShare);
}