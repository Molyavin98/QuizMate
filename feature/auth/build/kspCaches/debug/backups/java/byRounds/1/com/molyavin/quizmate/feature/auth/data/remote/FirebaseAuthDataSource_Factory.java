package com.molyavin.quizmate.feature.auth.data.remote;

import com.google.firebase.auth.FirebaseAuth;
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
public final class FirebaseAuthDataSource_Factory implements Factory<FirebaseAuthDataSource> {
  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public FirebaseAuthDataSource_Factory(Provider<FirebaseAuth> firebaseAuthProvider) {
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public FirebaseAuthDataSource get() {
    return newInstance(firebaseAuthProvider.get());
  }

  public static FirebaseAuthDataSource_Factory create(Provider<FirebaseAuth> firebaseAuthProvider) {
    return new FirebaseAuthDataSource_Factory(firebaseAuthProvider);
  }

  public static FirebaseAuthDataSource newInstance(FirebaseAuth firebaseAuth) {
    return new FirebaseAuthDataSource(firebaseAuth);
  }
}
