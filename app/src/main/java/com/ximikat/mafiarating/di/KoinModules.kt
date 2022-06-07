package com.ximikat.mafiarating.di

import com.ximikat.mafiarating.repository.GamesRepository
import com.ximikat.mafiarating.repository.GamesRepositoryImpl
import com.ximikat.mafiarating.repository.PlayersRepository
import com.ximikat.mafiarating.repository.PlayersRepositoryImpl
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel
import com.ximikat.mafiarating.ui.viewmodel.PlayersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PlayersListViewModel(get())
    }
    viewModel {
        GamesListViewModel(get())
    }
}

val repositoryModule = module(true) {
    single<GamesRepository> {
        GamesRepositoryImpl()
    }
    single<PlayersRepository> {
        PlayersRepositoryImpl()
    }
}