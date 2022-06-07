package com.ximikat.mafiarating.database

import androidx.room.Dao
import androidx.room.Query
import com.ximikat.mafiarating.model.GameDto
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM GameDto")
    fun getAll(): Flow<List<GameDto>>

}