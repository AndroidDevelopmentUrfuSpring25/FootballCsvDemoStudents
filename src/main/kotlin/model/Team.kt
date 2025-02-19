package model

data class Team(
    val name: String,
    val city: String,
    val players: List<Player>
) {
    fun totalTransferValue(): Double {
        return players.sumOf { it.transferValue }
    }

    fun averageRedCards(): Double {
        return if (players.isNotEmpty()) {
            players.sumOf { it.redCards }.toDouble() / players.size
        } else {
            0.0
        }
    }
}
