package com.ximikat.mafiarating.repository

import com.ximikat.mafiarating.database.GameDao
import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.model.domain.Player
import com.ximikat.mafiarating.model.domain.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*
    
class GamesRepositoryImpl(private val gameDao: GameDao): GamesRepository {

    override suspend fun getGames(): Flow<List<Game>> {
        return flowOf(
            listOf(
                Game(listOf(), 1, 1, 1, 1, Team.BLACK, Calendar.getInstance().time),
                Game(listOf(), 1, 1, 1, 1, Team.RED, Calendar.getInstance().time),
                Game(listOf(), 1, 1, 1, 1, Team.BLACK, Calendar.getInstance().time),
                Game(listOf(), 1, 1, 1, 1, Team.RED, Calendar.getInstance().time),
                Game(listOf(), 1, 1, 1, 1, Team.BLACK, Calendar.getInstance().time)
            )
        )/*gameDao.getAll().map { gameDtoList ->
            val formatter = SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
            gameDtoList.map { gameDto ->
                Game(
                    players = gameDto.rawPlayersStr.split('|').map {
                        val (nickname, bonus) = it.split(':')
                        Pair(Player(nickname), bonus.toDouble())
                    },
                    maf1 = gameDto.maf1, maf2 = gameDto.maf2,
                    sheriff = gameDto.sheriff, don = gameDto.don,
                    winningTeam = gameDto.winningTeam,
                    date = formatter.parse(gameDto.rawDateStr) ?: Calendar.getInstance().time
                )
            }
        }
    }*/
    }
}