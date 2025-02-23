package model

enum class Position(val russianName: String) {
    MIDFIELD("Полузащитник"),
    DEFENDER("Защитник"),
    GOALKEEPER("Вратарь"),
    FORWARD("Нападающий"),
    UNKNOWN("Неизвестно");

    companion object {
        fun getByValue(value: String): Position {
            return entries.find { it.name == value } ?: UNKNOWN
        }
    }
}