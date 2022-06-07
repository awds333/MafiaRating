package com.ximikat.mafiarating.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ximikat.mafiarating.model.GameDto

@Database(entities = [GameDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}