package com.molyavin.quizmate.feature.vocabulary.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFirestoreDataSource
import com.molyavin.quizmate.feature.vocabulary.data.repository.VocabularyRepositoryImpl
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt модуль для Vocabulary feature (тільки Firestore)
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class VocabularyModule {

    @Binds
    @Singleton
    abstract fun bindVocabularyRepository(
        impl: VocabularyRepositoryImpl
    ): VocabularyRepository

    companion object {
        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder().create()
        }

        @Provides
        @Singleton
        fun provideFirebaseFirestore(): FirebaseFirestore {
            return Firebase.firestore
        }

        @Provides
        @Singleton
        fun provideFirestoreDataSource(
            firestore: FirebaseFirestore,
            firebaseAuth: FirebaseAuth
        ): VocabularyFirestoreDataSource {
            return VocabularyFirestoreDataSource(firestore, firebaseAuth)
        }
    }
}
