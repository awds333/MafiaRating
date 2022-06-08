package com.ximikat.mafiarating.di

import androidx.room.Room
import com.ximikat.mafiarating.database.AppDatabase
import com.ximikat.mafiarating.repository.GamesRepository
import com.ximikat.mafiarating.repository.GamesRepositoryImpl
import com.ximikat.mafiarating.ui.viewmodel.GameConstructionViewModel
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel
import com.ximikat.mafiarating.ui.viewmodel.PlayerStatisticsViewModel
import com.ximikat.mafiarating.ui.viewmodel.PlayersListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PlayersListViewModel(get())
    }
    viewModel {
        GamesListViewModel(get())
    }
    viewModel {
        GameConstructionViewModel(get())
    }
    viewModel {
        PlayerStatisticsViewModel(get())
    }
}

val repositoryModule = module(true) {
    single<GamesRepository> {
        GamesRepositoryImpl(get())
    }
}

val databaseModule = module(true) {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "games-database"
        ).build()
    }
    single {
        get<AppDatabase>().gameDao()
    }
}