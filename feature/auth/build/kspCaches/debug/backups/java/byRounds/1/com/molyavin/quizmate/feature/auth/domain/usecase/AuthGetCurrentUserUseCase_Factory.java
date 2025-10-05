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
public final class AuthGetCurrentUserUseCase_Factory implements Factory<AuthGetCurrentUserUseCase> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public AuthGetCurrentUserUseCase_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public AuthGetCurrentUserUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static AuthGetCurrentUserUseCase_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new AuthGetCurrentUserUseCase_Factory(authRepositoryProvider);
  }

  public static AuthGetCurrentUserUseCase newInstance(AuthRepository authRepository) {
    return new AuthGetCurrentUserUseCase(authRepository);
  }
}
