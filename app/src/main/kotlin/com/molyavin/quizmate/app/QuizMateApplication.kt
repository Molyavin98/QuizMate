package com.molyavin.quizmate.app

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.google.firebase.FirebaseApp
import com.molyavin.quizmate.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class QuizMateApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        // Timber logging
        Timber.Forest.plant(Timber.DebugTree())

        // Firebase initialization
        FirebaseApp.initializeApp(this)

        // Koin initialization
        startKoin {
            androidLogger()
            androidContext(this@QuizMateApplication)
            modules(appModule)
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .respectCacheHeaders(false)
            .logger(DebugLogger())
            .build()
    }
}