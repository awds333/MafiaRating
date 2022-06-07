package com.ximikat.mafiarating.repository

import com.ximikat.mafiarating.model.domain.Game

class GamesRepositoryImpl: GamesRepository {

    override suspend fun getGames(): Flow<Game> {
        return emptyList()
    }

}