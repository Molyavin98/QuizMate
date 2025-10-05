package com.molyavin.quizmate.feature.vocabulary.data.local;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
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
public final class VocabularyFirestoreDataSource_Factory implements Factory<VocabularyFirestoreDataSource> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public VocabularyFirestoreDataSource_Factory(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    this.firestoreProvider = firestoreProvider;
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public VocabularyFirestoreDataSource get() {
    return newInstance(firestoreProvider.get(), firebaseAuthProvider.get());
  }

  public static VocabularyFirestoreDataSource_Factory create(
      Provider<FirebaseFirestore> firestoreProvider, Provider<FirebaseAuth> firebaseAuthProvider) {
    return new VocabularyFirestoreDataSource_Factory(firestoreProvider, firebaseAuthProvider);
  }

  public static VocabularyFirestoreDataSource newInstance(FirebaseFirestore firestore,
      FirebaseAuth firebaseAuth) {
    return new VocabularyFirestoreDataSource(firestore, firebaseAuth);
  }
}
