package ru.welokot.monopoly.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import ru.welokot.monopoly.utils.Palette
import java.io.Serializable
import java.util.Arrays.asList
import androidx.room.TypeConverters

@Entity(tableName = "players")
@TypeConverters(PaletteConverter::class)
data class Player(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "cash")
    val cash: Double,

    @ColumnInfo(name = "palette")
    val palette: Palette

): Serializable

class PaletteConverter {
    @TypeConverter
    fun fromPalette(palette: Palette): String {
        return palette.name
    }

    @TypeConverter
    fun toPalette(paletteName: String): Palette {
        return Palette.valueOf(paletteName)
    }
}