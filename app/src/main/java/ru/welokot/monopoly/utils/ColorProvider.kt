package ru.welokot.monopoly.utils

object ColorProvider {

    private val unusedColors = Palette.values().toMutableSet()

    fun getNextUnusedPalette(): Palette {
        if (unusedColors.isNotEmpty()) {
            val color = unusedColors.random()
            unusedColors.remove(color)
            return color
        }
        return getRandomPalette()
    }

    private fun getRandomPalette(): Palette {
        return Palette.values().random()
    }

    fun setNewUnusedPalette(color: Palette) {
        unusedColors.add(color)
    }
}

enum class Palette (val bgHex: String, val textHex: String) {
    RED("F44336", "FFFFFF"),
    PINK("E91E63", "FFFFFF"),
    PURPLE("9C27B0", "FFFFFF"),
    DEEP_PURPLE("673AB7", "FFFFFF"),
    INDIGO("3F52B5", "FFFFFF"),
    BLUE("2196F3", "FFFFFF"),
    LIGHT_BLUE("03A9F4", "FFFFFF"),
    CYAN("00BCD4", "FFFFFF"),
    TEAL("009688", "FFFFFF"),
    GREEN("4CAF50", "FFFFFF"),
    LIGHT_GREEN("8BC34A", "3E464C"),
    LIME("CDDC39", "3E464C"),
    YELLOW("FFEB3B", "3E464C"),
    AMBER("FFC107", "3E464C"),
    ORANGE("FF9800", "3E464C"),
    DEEP_ORANGE("FF5722", "FFFFFF"),
    BROWN("795548", "FFFFFF"),
    GREY("9E9E9E", "3E464C"),
    BLUE_GREY("607D8B", "FFFFFF")
}