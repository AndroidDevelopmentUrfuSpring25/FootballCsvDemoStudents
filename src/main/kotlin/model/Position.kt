package model

enum class Position(val russianName: String) {
    MIDFIELD("Полузащитник"),
    DEFENDER("Защитник"),
    FORWARD("Нападающий"),
    GOALKEEPER("Вратарь"),
    UNKNOWN("NaN");

    companion object {
        fun parse(posName: String): Position {
            return entries.find { it.name == posName } ?: UNKNOWN
        }
    }
}