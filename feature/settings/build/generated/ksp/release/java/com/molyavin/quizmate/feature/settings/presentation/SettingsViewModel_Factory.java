package com.molyavin.quizmate.feature.settings.presentation;

import com.molyavin.quizmate.feature.auth.domain.usecase.AuthGetCurrentUserUseCase;
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignOutUseCase;
import com.molyavin.quizmate.feature.settings.domain.repository.SettingsRepository;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<AuthGetCurrentUserUseCase> authGetCurrentUserUseCaseProvider;

  private final Provider<AuthSignOutUseCase> authSignOutUseCaseProvider;

  public SettingsViewModel_Factory(Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<AuthGetCurrentUserUseCase> authGetCurrentUserUseCaseProvider,
      Provider<AuthSignOutUseCase> authSignOutUseCaseProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.authGetCurrentUserUseCaseProvider = authGetCurrentUserUseCaseProvider;
    this.authSignOutUseCaseProvider = authSignOutUseCaseProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(settingsRepositoryProvider.get(), authGetCurrentUserUseCaseProvider.get(), authSignOutUseCaseProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<AuthGetCurrentUserUseCase> authGetCurrentUserUseCaseProvider,
      Provider<AuthSignOutUseCase> authSignOutUseCaseProvider) {
    return new SettingsViewModel_Factory(settingsRepositoryProvider, authGetCurrentUserUseCaseProvider, authSignOutUseCaseProvider);
  }

  public static SettingsViewModel newInstance(SettingsRepository settingsRepository,
      AuthGetCurrentUserUseCase authGetCurrentUserUseCase, AuthSignOutUseCase authSignOutUseCase) {
    return new SettingsViewModel(settingsRepository, authGetCurrentUserUseCase, authSignOutUseCase);
  }
}
