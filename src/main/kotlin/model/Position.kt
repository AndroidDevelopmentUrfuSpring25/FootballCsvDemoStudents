package model

enum class Position(val russianName: String) {
    GOALKEEPER("Вратарь"),
    MIDFIELD("Полузащитник"),
    DEFENDER("Защитник"),
    FORWARD("Нападающий");

    companion object {
        fun from(s: String): Position {
            return entries.find { it.name == s } ?: throw IllegalArgumentException("Unknown Position")
        }
    }
}