package com.molyavin.quizmate.feature.auth.presentation.ui.login;

import com.molyavin.quizmate.feature.auth.domain.usecase.AuthObserveAuthStateUseCase;
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignInWithEmailUseCase;
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignInWithGoogleUseCase;
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
public final class AuthLoginViewModel_Factory implements Factory<AuthLoginViewModel> {
  private final Provider<AuthSignInWithEmailUseCase> authSignInWithEmailUseCaseProvider;

  private final Provider<AuthSignInWithGoogleUseCase> authSignInWithGoogleUseCaseProvider;

  private final Provider<AuthObserveAuthStateUseCase> authObserveAuthStateUseCaseProvider;

  public AuthLoginViewModel_Factory(
      Provider<AuthSignInWithEmailUseCase> authSignInWithEmailUseCaseProvider,
      Provider<AuthSignInWithGoogleUseCase> authSignInWithGoogleUseCaseProvider,
      Provider<AuthObserveAuthStateUseCase> authObserveAuthStateUseCaseProvider) {
    this.authSignInWithEmailUseCaseProvider = authSignInWithEmailUseCaseProvider;
    this.authSignInWithGoogleUseCaseProvider = authSignInWithGoogleUseCaseProvider;
    this.authObserveAuthStateUseCaseProvider = authObserveAuthStateUseCaseProvider;
  }

  @Override
  public AuthLoginViewModel get() {
    return newInstance(authSignInWithEmailUseCaseProvider.get(), authSignInWithGoogleUseCaseProvider.get(), authObserveAuthStateUseCaseProvider.get());
  }

  public static AuthLoginViewModel_Factory create(
      Provider<AuthSignInWithEmailUseCase> authSignInWithEmailUseCaseProvider,
      Provider<AuthSignInWithGoogleUseCase> authSignInWithGoogleUseCaseProvider,
      Provider<AuthObserveAuthStateUseCase> authObserveAuthStateUseCaseProvider) {
    return new AuthLoginViewModel_Factory(authSignInWithEmailUseCaseProvider, authSignInWithGoogleUseCaseProvider, authObserveAuthStateUseCaseProvider);
  }

  public static AuthLoginViewModel newInstance(
      AuthSignInWithEmailUseCase authSignInWithEmailUseCase,
      AuthSignInWithGoogleUseCase authSignInWithGoogleUseCase,
      AuthObserveAuthStateUseCase authObserveAuthStateUseCase) {
    return new AuthLoginViewModel(authSignInWithEmailUseCase, authSignInWithGoogleUseCase, authObserveAuthStateUseCase);
  }
}
