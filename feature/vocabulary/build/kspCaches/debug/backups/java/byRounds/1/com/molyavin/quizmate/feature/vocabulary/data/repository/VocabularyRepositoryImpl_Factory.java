package com.molyavin.quizmate.feature.vocabulary.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.molyavin.quizmate.feature.vocabulary.data.local.FirestoreDataSource;
import com.molyavin.quizmate.feature.vocabulary.data.local.FolderDao;
import com.molyavin.quizmate.feature.vocabulary.data.local.WordDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class VocabularyRepositoryImpl_Factory implements Factory<VocabularyRepositoryImpl> {
  private final Provider<WordDao> wordDaoProvider;

  private final Provider<FolderDao> folderDaoProvider;

  private final Provider<FirestoreDataSource> firestoreDataSourceProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public VocabularyRepositoryImpl_Factory(Provider<WordDao> wordDaoProvider,
      Provider<FolderDao> folderDaoProvider,
      Provider<FirestoreDataSource> firestoreDataSourceProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    this.wordDaoProvider = wordDaoProvider;
    this.folderDaoProvider = folderDaoProvider;
    this.firestoreDataSourceProvider = firestoreDataSourceProvider;
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public VocabularyRepositoryImpl get() {
    return newInstance(wordDaoProvider.get(), folderDaoProvider.get(), firestoreDataSourceProvider.get(), firebaseAuthProvider.get());
  }

  public static VocabularyRepositoryImpl_Factory create(Provider<WordDao> wordDaoProvider,
      Provider<FolderDao> folderDaoProvider,
      Provider<FirestoreDataSource> firestoreDataSourceProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    return new VocabularyRepositoryImpl_Factory(wordDaoProvider, folderDaoProvider, firestoreDataSourceProvider, firebaseAuthProvider);
  }

  public static VocabularyRepositoryImpl newInstance(WordDao wordDao, FolderDao folderDao,
      FirestoreDataSource firestoreDataSource, FirebaseAuth firebaseAuth) {
    return new VocabularyRepositoryImpl(wordDao, folderDao, firestoreDataSource, firebaseAuth);
  }
}
