package com.molyavin.quizmate.favorites.di

import com.molyavin.quizmate.favorites.presentation.ui.FavoritesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favoritesModule = module {
    viewModelOf(::FavoritesViewModel)
}
