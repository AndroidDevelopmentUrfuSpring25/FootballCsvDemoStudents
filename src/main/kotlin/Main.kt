import resolver.Resolver
import utils.ChartDrawer.createAndShowChart

fun main() {
    val resolver = Resolver()
    val nationalityShare = Resolver().getNationalityShare()
    val countAgencyLessPlayers = resolver.getCountWithoutAgency()
    val bestDefenderPlayer = resolver.getBestScorerDefender()
    val mostExpensiveGermanPlayer = resolver.getTheMostExpensiveGermanPlayerPosition()
    val rudestTeam = resolver.getTheRudestTeam()

    println("1. Выведите количество игроков, интересы которых не представляет агенство. - $countAgencyLessPlayers")
    println("2. Выведите автора наибольшего числа голов - ${bestDefenderPlayer.first} из числа защитников и их количество. - ${bestDefenderPlayer.second}")
    println("3. Выведите русское название позиции самого дорогого немецкого игрока - $mostExpensiveGermanPlayer")
    println("4. Выберите команду с наибольшим средним числом красных карточек на одного игрока. - ${rudestTeam.name}")

    createAndShowChart(nationalityShare)
}

