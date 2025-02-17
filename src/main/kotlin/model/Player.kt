package model

/**
 * Класс данных футболиста
 */
class Player (
    val name: String?,
    val positionOnField: PositionTranslation,
    val nationality: String?,
    val agency: String?,
    val transferCost: Int?,
    val participations: Int?,
    val goals: Int?,
    val assists: Int?,
    val yellowCards: Int?,
    val redCards: Int?
)
