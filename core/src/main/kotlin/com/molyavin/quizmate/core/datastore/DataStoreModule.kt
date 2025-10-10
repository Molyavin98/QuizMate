package com.molyavin.quizmate.core.datastore

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { PreferencesManager(androidContext()) }
}
