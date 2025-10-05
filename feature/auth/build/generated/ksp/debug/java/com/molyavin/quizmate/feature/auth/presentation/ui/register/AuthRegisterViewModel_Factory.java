package com.molyavin.quizmate.feature.auth.presentation.ui.register;

import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignUpWithEmailUseCase;
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
public final class AuthRegisterViewModel_Factory implements Factory<AuthRegisterViewModel> {
  private final Provider<AuthSignUpWithEmailUseCase> authSignUpWithEmailUseCaseProvider;

  public AuthRegisterViewModel_Factory(
      Provider<AuthSignUpWithEmailUseCase> authSignUpWithEmailUseCaseProvider) {
    this.authSignUpWithEmailUseCaseProvider = authSignUpWithEmailUseCaseProvider;
  }

  @Override
  public AuthRegisterViewModel get() {
    return newInstance(authSignUpWithEmailUseCaseProvider.get());
  }

  public static AuthRegisterViewModel_Factory create(
      Provider<AuthSignUpWithEmailUseCase> authSignUpWithEmailUseCaseProvider) {
    return new AuthRegisterViewModel_Factory(authSignUpWithEmailUseCaseProvider);
  }

  public static AuthRegisterViewModel newInstance(
      AuthSignUpWithEmailUseCase authSignUpWithEmailUseCase) {
    return new AuthRegisterViewModel(authSignUpWithEmailUseCase);
  }
}
