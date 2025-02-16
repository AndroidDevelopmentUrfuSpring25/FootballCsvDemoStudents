package model

enum class Position(val russianName: String) {
    MIDFIELD("Полузащитник"),
    DEFENDER("Защитник"),
    GOALKEEPER("Вратарь"),
    FORWARD("Нападающий");

    companion object {
        fun getByValue(s: String): Position {
            return entries.find { it.name.equals(s, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown position: $s")
        }
    }
}

