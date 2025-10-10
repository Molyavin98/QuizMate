package com.molyavin.quizmate.feature.auth.di;

import com.molyavin.quizmate.feature.auth.data.remote.FirebaseAuthDataSource;
import com.molyavin.quizmate.feature.auth.domain.repository.AuthRepository;
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
public final class AuthInternalModule_ProvideAuthRepositoryFactory implements Factory<AuthRepository> {
  private final AuthInternalModule module;

  private final Provider<FirebaseAuthDataSource> firebaseAuthDataSourceProvider;

  public AuthInternalModule_ProvideAuthRepositoryFactory(AuthInternalModule module,
      Provider<FirebaseAuthDataSource> firebaseAuthDataSourceProvider) {
    this.module = module;
    this.firebaseAuthDataSourceProvider = firebaseAuthDataSourceProvider;
  }

  @Override
  public AuthRepository get() {
    return provideAuthRepository(module, firebaseAuthDataSourceProvider.get());
  }

  public static AuthInternalModule_ProvideAuthRepositoryFactory create(AuthInternalModule module,
      Provider<FirebaseAuthDataSource> firebaseAuthDataSourceProvider) {
    return new AuthInternalModule_ProvideAuthRepositoryFactory(module, firebaseAuthDataSourceProvider);
  }

  public static AuthRepository provideAuthRepository(AuthInternalModule instance,
      FirebaseAuthDataSource firebaseAuthDataSource) {
    return Preconditions.checkNotNullFromProvides(instance.provideAuthRepository(firebaseAuthDataSource));
  }
}
