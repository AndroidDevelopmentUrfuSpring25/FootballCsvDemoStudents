package resolver

import model.Player

interface IResolver {
    fun countPlayersWithoutAgency(players: List<Player>): Int
    fun topScoringDefender(players: List<Player>): Pair<String, Int>?
    fun mostExpensiveGermanPlayerPosition(players: List<Player>): String?
    fun teamWithMostRedCards(players: List<Player>): String?
    fun top10TeamsByTransferValue(players: List<Player>): List<Pair<String, Double>>
}
