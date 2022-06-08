package com.ximikat.mafiarating.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.ximikat.mafiarating.ui.viewmodel.ConstructionStep
import com.ximikat.mafiarating.ui.viewmodel.GameConstructionViewModel
import com.ximikat.mafiarating.ui.viewmodel.isMafia

@Composable
fun GameConstructionScreenCompose(viewModel: GameConstructionViewModel) {

    val state = viewModel.mainState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar { Text(text = "New game") }
        },
        // modifier = Modifier.verticalScroll(rememberScrollState())
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            val itemView: @Composable (Int, String) -> Unit =
                when (state.value.currentStep) {
                    ConstructionStep.NicknameInputStep -> { index, nickname ->
                        TextField(
                            value = nickname,
                            onValueChange = { viewModel.setNickname(index, it) },
                            placeholder = { Text(text = "Player ${index + 1}") },
                            keyboardOptions = KeyboardOptions.Default,
                            maxLines = 1
                        )
                    }
                    ConstructionStep.MafiaInputStep -> { index, nickname ->
                        TextField(
                            value = nickname,
                            readOnly = true,
                            onValueChange = {},
                            trailingIcon = {
                                Switch(
                                    checked = state.value.isMafia(index),
                                    onCheckedChange = {
                                        viewModel.toggleMafiaButton(index)
                                    }
                                )
                            }
                        )
                    }
                    else -> { index, nickname -> TODO() }
                }

            state.value.playerNicknames.forEachIndexed { index, nickname ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemView(index, nickname)
                }
            }

            val (enabled, nextStep) =
                when (state.value.currentStep) {
                    ConstructionStep.NicknameInputStep ->
                        state.value.allNicknamesChosen to ConstructionStep.MafiaInputStep
                    ConstructionStep.MafiaInputStep ->
                        state.value.mafiaSelected to ConstructionStep.SheriffDonAndInputStep
                    ConstructionStep.SheriffDonAndInputStep ->
                        state.value.donAndSheriffSelected to ConstructionStep.FinalInputStep
                    ConstructionStep.FinalInputStep ->
                        state.value.winningTeamSelected to ConstructionStep.FinalInputStep
                }

            val finalStep = state.value.currentStep == ConstructionStep.FinalInputStep

            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled && finalStep.not(),
                onClick = {
                    if (finalStep) {
                        // TODO: Save the game and nav
                    } else {
                        viewModel.setCurrentStep(nextStep)
                    }
                }
            ) {
                Text(text = if (finalStep) "Save game" else "Continue")
            }

        }

    }

}