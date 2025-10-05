package com.molyavin.quizmate.feature.vocabulary.di;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFirestoreDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class VocabularyModule_Companion_ProvideFirestoreDataSourceFactory implements Factory<VocabularyFirestoreDataSource> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public VocabularyModule_Companion_ProvideFirestoreDataSourceFactory(
      Provider<FirebaseFirestore> firestoreProvider, Provider<FirebaseAuth> firebaseAuthProvider) {
    this.firestoreProvider = firestoreProvider;
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public VocabularyFirestoreDataSource get() {
    return provideFirestoreDataSource(firestoreProvider.get(), firebaseAuthProvider.get());
  }

  public static VocabularyModule_Companion_ProvideFirestoreDataSourceFactory create(
      Provider<FirebaseFirestore> firestoreProvider, Provider<FirebaseAuth> firebaseAuthProvider) {
    return new VocabularyModule_Companion_ProvideFirestoreDataSourceFactory(firestoreProvider, firebaseAuthProvider);
  }

  public static VocabularyFirestoreDataSource provideFirestoreDataSource(FirebaseFirestore firestore,
                                                                         FirebaseAuth firebaseAuth) {
    return Preconditions.checkNotNullFromProvides(VocabularyModule.Companion.provideFirestoreDataSource(firestore, firebaseAuth));
  }
}
