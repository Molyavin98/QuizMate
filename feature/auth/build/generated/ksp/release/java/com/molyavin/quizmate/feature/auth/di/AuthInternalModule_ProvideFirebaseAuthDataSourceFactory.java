package com.molyavin.quizmate.feature.auth.di;

import com.google.firebase.auth.FirebaseAuth;
import com.molyavin.quizmate.feature.auth.data.remote.FirebaseAuthDataSource;
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
public final class AuthInternalModule_ProvideFirebaseAuthDataSourceFactory implements Factory<FirebaseAuthDataSource> {
  private final AuthInternalModule module;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public AuthInternalModule_ProvideFirebaseAuthDataSourceFactory(AuthInternalModule module,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    this.module = module;
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public FirebaseAuthDataSource get() {
    return provideFirebaseAuthDataSource(module, firebaseAuthProvider.get());
  }

  public static AuthInternalModule_ProvideFirebaseAuthDataSourceFactory create(
      AuthInternalModule module, Provider<FirebaseAuth> firebaseAuthProvider) {
    return new AuthInternalModule_ProvideFirebaseAuthDataSourceFactory(module, firebaseAuthProvider);
  }

  public static FirebaseAuthDataSource provideFirebaseAuthDataSource(AuthInternalModule instance,
      FirebaseAuth firebaseAuth) {
    return Preconditions.checkNotNullFromProvides(instance.provideFirebaseAuthDataSource(firebaseAuth));
  }
}
