package com.molyavin.quizmate.feature.vocabulary.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFirestoreDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
  private final Provider<VocabularyFirestoreDataSource> firestoreDataSourceProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public VocabularyRepositoryImpl_Factory(
      Provider<VocabularyFirestoreDataSource> firestoreDataSourceProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    this.firestoreDataSourceProvider = firestoreDataSourceProvider;
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public VocabularyRepositoryImpl get() {
    return newInstance(firestoreDataSourceProvider.get(), firebaseAuthProvider.get());
  }

  public static VocabularyRepositoryImpl_Factory create(
      Provider<VocabularyFirestoreDataSource> firestoreDataSourceProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    return new VocabularyRepositoryImpl_Factory(firestoreDataSourceProvider, firebaseAuthProvider);
  }

  public static VocabularyRepositoryImpl newInstance(
      VocabularyFirestoreDataSource firestoreDataSource, FirebaseAuth firebaseAuth) {
    return new VocabularyRepositoryImpl(firestoreDataSource, firebaseAuth);
  }
}
