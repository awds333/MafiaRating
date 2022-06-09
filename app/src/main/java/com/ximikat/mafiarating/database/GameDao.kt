package com.ximikat.mafiarating.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ximikat.mafiarating.model.GameDto
import com.ximikat.mafiarating.model.domain.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM GameDto")
    fun getAll(): Flow<List<GameDto>>

    @Insert
    suspend fun insertGame(game: GameDto)

    @Delete
    suspend fun deleteGame(game: GameDto)

}