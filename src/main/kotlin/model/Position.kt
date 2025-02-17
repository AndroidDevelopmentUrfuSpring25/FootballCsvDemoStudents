package model

enum class Position(val russianPositionName: String) {
    MIDFIELD("Полузащита"),
    DEFENDER("Защитник"),
    FORWARD("Нападающий"),
    GOALKEEPER("Вратарь");

    companion object {
        fun from(inputName: String?): Position =
            entries.find { it.name == inputName } ?: throw IllegalArgumentException()
    }
}