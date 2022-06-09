package com.ximikat.mafiarating.repository

import com.ximikat.mafiarating.model.domain.Game
import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    fun getGames(): Flow<List<Game>>
    suspend fun addGame(game: Game)
    suspend fun deleteGame(game: Game)

}