package com.ximikat.mafiarating.repository

import com.ximikat.mafiarating.model.domain.Game
import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    suspend fun getGames(): Flow<List<Game>>

}