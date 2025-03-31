package extensions

object IntExtensions {
    fun Int?.orZero(): Int {
        if (this == null) return 0
        return this
    }
}