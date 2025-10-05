package com.molyavin.quizmate.feature.vocabulary.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.molyavin.quizmate.feature.vocabulary.data.room.VocabularyDatabase
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFolderDao
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordDao
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFirestoreDataSource
import com.molyavin.quizmate.feature.vocabulary.data.repository.VocabularyRepositoryImpl
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt модуль для Vocabulary feature
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
        fun provideVocabularyDatabase(
            @ApplicationContext context: Context
        ): VocabularyDatabase {
            return Room.databaseBuilder(
                context,
                klass = VocabularyDatabase::class.java,
                name = VocabularyDatabase.DATABASE_NAME
            )
                .addMigrations(VocabularyDatabase.MIGRATION_4_5)
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        @Singleton
        fun provideWordDao(database: VocabularyDatabase): VocabularyWordDao {
            return database.wordDao()
        }

        @Provides
        @Singleton
        fun provideFolderDao(database: VocabularyDatabase): VocabularyFolderDao {
            return database.folderDao()
        }

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
