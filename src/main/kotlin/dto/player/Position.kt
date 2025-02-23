package dto.player

import java.util.stream.Collectors

enum class Position {

    MIDFIELD,
    DEFENDER,
    FORWARD,
    GOALKEEPER,
    UNKNOWN;

    companion object {
        private val all = entries.stream().collect(
            Collectors.toMap(
                { it.name },
                { it })
        )

        fun getOrDefault(position: String): Position = all.getOrDefault(position, UNKNOWN)
    }
}