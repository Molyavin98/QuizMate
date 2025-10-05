package com.molyavin.quizmate.feature.auth.data.repository;

import com.molyavin.quizmate.feature.auth.data.remote.FirebaseAuthDataSource;
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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<FirebaseAuthDataSource> firebaseAuthDataSourceProvider;

  public AuthRepositoryImpl_Factory(
      Provider<FirebaseAuthDataSource> firebaseAuthDataSourceProvider) {
    this.firebaseAuthDataSourceProvider = firebaseAuthDataSourceProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(firebaseAuthDataSourceProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(
      Provider<FirebaseAuthDataSource> firebaseAuthDataSourceProvider) {
    return new AuthRepositoryImpl_Factory(firebaseAuthDataSourceProvider);
  }

  public static AuthRepositoryImpl newInstance(FirebaseAuthDataSource firebaseAuthDataSource) {
    return new AuthRepositoryImpl(firebaseAuthDataSource);
  }
}
