package com.molyavin.quizmate.feature.vocabulary.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFirestoreDataSource
import com.molyavin.quizmate.feature.vocabulary.data.repository.VocabularyRepositoryImpl
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.AddWordUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.CreateFolderUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.DeleteAllWordsUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.DeleteFolderUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.DeleteWordUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllFoldersUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllWordsUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetWordsByFolderUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.SearchWordsUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.VocabularySyncFromFirestoreUseCase
import com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryViewModel
import com.molyavin.quizmate.feature.vocabulary.presentation.FolderDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val vocabularyModule = module {
    single<Gson> { GsonBuilder().create() }
    single<FirebaseFirestore> { Firebase.firestore }
    singleOf(::VocabularyFirestoreDataSource)
    single<VocabularyRepository> { VocabularyRepositoryImpl(get(), get()) }

    factoryOf(::AddWordUseCase)
    factoryOf(::CreateFolderUseCase)
    factoryOf(::DeleteAllWordsUseCase)
    factoryOf(::DeleteFolderUseCase)
    factoryOf(::DeleteWordUseCase)
    factoryOf(::GetAllFoldersUseCase)
    factoryOf(::GetAllWordsUseCase)
    factoryOf(::GetWordsByFolderUseCase)
    factoryOf(::ImportWordsFromJsonUseCase)
    factoryOf(::SearchWordsUseCase)
    factoryOf(::VocabularySyncFromFirestoreUseCase)

    viewModelOf(::DictionaryViewModel)
    viewModelOf(::FolderDetailsViewModel)
}
