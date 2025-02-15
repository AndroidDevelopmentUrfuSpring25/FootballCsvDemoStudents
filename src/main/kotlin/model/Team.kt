package model

/**
 * Класс данных футбольной команды
 */
class Team(
    val name: String,
    val city: String
){
    private var players: MutableSet<Player> = mutableSetOf()

    /**
     * Добавляет игрока в команду
     */
    fun addPlayer(player: Player){
        players.add(player)
    }

    fun getPlayers():Set<Player>{
        return players.toSet()
    }
}