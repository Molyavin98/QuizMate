package com.molyavin.quizmate.feature.auth.domain.usecase;

import com.molyavin.quizmate.feature.auth.domain.repository.AuthRepository;
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
public final class AuthSignInWithEmailUseCase_Factory implements Factory<AuthSignInWithEmailUseCase> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public AuthSignInWithEmailUseCase_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public AuthSignInWithEmailUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static AuthSignInWithEmailUseCase_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new AuthSignInWithEmailUseCase_Factory(authRepositoryProvider);
  }

  public static AuthSignInWithEmailUseCase newInstance(AuthRepository authRepository) {
    return new AuthSignInWithEmailUseCase(authRepository);
  }
}
