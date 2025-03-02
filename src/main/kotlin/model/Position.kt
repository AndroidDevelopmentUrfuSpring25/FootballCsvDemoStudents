package model

enum class Position(val posName: String) {
    MIDFIELD("Полузащита"),
    DEFENDER("Защитник"),
    FORWARD("Нападающий"),
    GOALKEEPER("Вратарь"),
    DEFAULT("none");

    companion object {
        fun stringToPosition(value: String): Position {
            return entries.find { it.name == value } ?: DEFAULT
        }
    }

}