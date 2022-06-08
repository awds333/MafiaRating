package com.ximikat.mafiarating.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ximikat.mafiarating.model.domain.Team
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
data class GameDto (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @NotNull @ColumnInfo(name = "raw_players") val rawPlayersStr: String,
    @NotNull @ColumnInfo(name = "mafia1") val maf1: Int,
    @NotNull @ColumnInfo(name = "mafia2") val maf2: Int,
    @NotNull @ColumnInfo(name = "sheriff") val sheriff: Int,
    @NotNull @ColumnInfo(name = "don") val don: Int,
    @NotNull @ColumnInfo(name = "winning_team") val winningTeam: Team,
    @NotNull @ColumnInfo(name = "raw_date") val rawDateStr: String
)