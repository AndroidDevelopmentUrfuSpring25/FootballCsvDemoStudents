package model

enum class Position(val russianName: String) {
    MIDFIELD("Полузащитник"),
    DEFENDER("Защитник"),
    GOALKEEPER("Вратарь"),
    FORWARD("Нападающий"),
    NONE("Не указано");

    companion object {
        fun getByValue(value: String): Position {
            return entries.find { it.name == value } ?: NONE
        }
    }
}


