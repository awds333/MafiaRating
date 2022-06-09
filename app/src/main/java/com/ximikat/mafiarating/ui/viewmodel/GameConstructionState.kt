package com.ximikat.mafiarating.ui.viewmodel

import com.ximikat.mafiarating.model.domain.Team
import java.util.*

data class GameConstructionState(
    val playerNicknames: List<String> = List(10) { "" },
    val mafia: List<Int> = emptyList(),
    val sheriff: Int? = null,
    val don: Int? = null,
    val winningTeam: Team? = null,
    val date: Date = Calendar.getInstance().time,
    val currentStep: ConstructionStep = ConstructionStep.NicknameInputStep
) {
    val allNicknamesChosen: Boolean
        get() = playerNicknames.none { it == "" }
    val mafiaSelected: Boolean
        get() = mafia.size == 3
    val donAndSheriffSelected: Boolean
        get() = sheriff != null && don != null
    val winningTeamSelected: Boolean
        get() = winningTeam != null
}

fun GameConstructionState.isMafia(index: Int) = mafia.contains(index)

fun GameConstructionState.isWinning(index: Int) = isMafia(index) == (winningTeam == Team.BLACK)

enum class ConstructionStep {
    NicknameInputStep, MafiaInputStep, SheriffDonAndInputStep, FinalInputStep
}