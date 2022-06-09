package com.ximikat.mafiarating.repository

import com.ximikat.mafiarating.database.GameDao
import com.ximikat.mafiarating.model.GameDto
import com.ximikat.mafiarating.model.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*

class GamesRepositoryImpl(private val gameDao: GameDao) : GamesRepository {

    override fun getGames(): Flow<List<Game>> {
        return gameDao.getAll().map { gameDtoList ->
            val formatter = SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
            gameDtoList.map { gameDto ->
                Game(
                    id = gameDto.id,
                    entries = gameDto.rawPlayersStr.split('|').map {
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
    }

    override suspend fun addGame(game: Game) {
        val formatter = SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
        gameDao.insertGame(
            GameDto(
                rawPlayersStr = game.entries.joinToString(separator = "|") { (player, points) ->
                    "${player.nickname}:$points"
                },
                maf1 = game.maf1,
                maf2 = game.maf2,
                sheriff = game.sheriff,
                don = game.don,
                winningTeam = game.winningTeam,
                rawDateStr = formatter.format(game.date)
            )
        )
    }

    override suspend fun deleteGame(game: Game) {
        val formatter = SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
        gameDao.deleteGame(
            GameDto(
                id = game.id!!,
                rawPlayersStr = game.entries.joinToString(separator = "|") { (player, points) ->
                    "${player.nickname}:$points"
                },
                maf1 = game.maf1,
                maf2 = game.maf2,
                sheriff = game.sheriff,
                don = game.don,
                winningTeam = game.winningTeam,
                rawDateStr = formatter.format(game.date)
            )
        )
    }

}