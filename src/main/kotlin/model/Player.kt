package model

data class Player(
    val name: String = "",
    var team: Team,
    var nationality: String = "",
    var position: String = "",
    var agency: String = "",
    var transferPrice: Int = 0,
    var playedGames: Int = 0,
    var goals: Int = 0,
    var assists: Int = 0,
    var yellowCardCount: Int = 0,
    var redCardCount: Int = 0

)
