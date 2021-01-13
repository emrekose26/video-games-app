package com.emrekose.videogames.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emrekose.videogames.ui.model.GameItem

@Database(entities = [GameItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}