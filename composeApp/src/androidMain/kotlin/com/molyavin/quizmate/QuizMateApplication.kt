package com.molyavin.quizmate

import android.app.Application
import com.molyavin.quizmate.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class QuizMateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Инициализация Timber для логирования
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Инициализация Koin
        startKoin {
            androidLogger()
            androidContext(this@QuizMateApplication)
            modules(appModule)
        }

        Timber.d("QuizMate application started")
    }
}
