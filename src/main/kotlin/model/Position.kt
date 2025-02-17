package model

enum class Position(val russianName: String) {
    MIDFIELD("Полузащитник"),
    DEFENDER("Защитник"),
    GOALKEEPER("Вратарь"),
    FORWARD("Нападающий"),
    NONE("Не указано"),
    UNKNOWN("Неизвестно");

    companion object {
        fun getByValue(value: String): Position {
            if (value == "") return NONE
            return entries.find { it.name == value } ?: UNKNOWN
        }
    }
}


