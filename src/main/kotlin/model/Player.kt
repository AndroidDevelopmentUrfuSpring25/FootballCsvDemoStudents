package model

class Player(
    val name : String,
    val team : Team,
    val position : Position,
    val nationality : String,
    val agency : String,
    val transferCost: Int,
    val participations: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int
){
    companion object {
        fun fromValues(values: List<String>) : Player = Player(
            name = values[0],
            team = Team(values[1], values[2]),
            position = Position.valueOf(values[3]),
            nationality =  values[4],
            agency = values[5],
            transferCost = values[6].toInt(),
            participations = values[7].toInt(),
            goals = values[8].toInt(),
            assists = values[9].toInt(),
            yellowCards = values[10].toInt(),
            redCards = values[11].toInt()
        )
    }
}