package ru.welokot.monopoly.db.entity.gameSession

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.welokot.monopoly.db.entity.gameMove.GameMoveEntity
import ru.welokot.monopoly.db.entity.player.PlayerEntity

object GameSessionConverter {

    @TypeConverter
    @JvmStatic
    fun fromPlayersList(playersList: List<PlayerEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<PlayerEntity>>() {}.type
        return gson.toJson(playersList, type)
    }

    @TypeConverter
    @JvmStatic
    fun toPlayersList(playersList: String): List<PlayerEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<PlayerEntity>>() {}.type
        return gson.fromJson(playersList, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromGameMovesList(gameMovesList: MutableList<GameMoveEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<MutableList<GameMoveEntity>>() {}.type
        return gson.toJson(gameMovesList, type)
    }

    @TypeConverter
    @JvmStatic
    fun toGameMovesList(gameMovesList: String): MutableList<GameMoveEntity> {
        val gson = Gson()
        val type = object : TypeToken<MutableList<GameMoveEntity>>() {}.type
        return gson.fromJson(gameMovesList, type)
    }
}
