package com.molyavin.quizmate.feature.vocabulary.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFirestoreDataSource;
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFolderDao;
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordDao;
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
  private final Provider<VocabularyWordDao> wordDaoProvider;

  private final Provider<VocabularyFolderDao> folderDaoProvider;

  private final Provider<VocabularyFirestoreDataSource> firestoreDataSourceProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public VocabularyRepositoryImpl_Factory(Provider<VocabularyWordDao> wordDaoProvider,
      Provider<VocabularyFolderDao> folderDaoProvider,
      Provider<VocabularyFirestoreDataSource> firestoreDataSourceProvider,
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

  public static VocabularyRepositoryImpl_Factory create(Provider<VocabularyWordDao> wordDaoProvider,
      Provider<VocabularyFolderDao> folderDaoProvider,
      Provider<VocabularyFirestoreDataSource> firestoreDataSourceProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    return new VocabularyRepositoryImpl_Factory(wordDaoProvider, folderDaoProvider, firestoreDataSourceProvider, firebaseAuthProvider);
  }

  public static VocabularyRepositoryImpl newInstance(VocabularyWordDao vocabularyWordDao, VocabularyFolderDao vocabularyFolderDao,
                                                     VocabularyFirestoreDataSource vocabularyFirestoreDataSource, FirebaseAuth firebaseAuth) {
    return new VocabularyRepositoryImpl(vocabularyWordDao, vocabularyFolderDao, vocabularyFirestoreDataSource, firebaseAuth);
  }
}
