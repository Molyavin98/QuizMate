package com.molyavin.quizmate.feature.settings.di;

import com.google.firebase.auth.FirebaseAuth;
import com.molyavin.quizmate.feature.settings.data.local.SettingsDataStore;
import com.molyavin.quizmate.feature.settings.domain.repository.SettingsRepository;
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
public final class SettingsModule_ProvideSettingsRepositoryFactory implements Factory<SettingsRepository> {
  private final Provider<SettingsDataStore> settingsDataStoreProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public SettingsModule_ProvideSettingsRepositoryFactory(
      Provider<SettingsDataStore> settingsDataStoreProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    this.settingsDataStoreProvider = settingsDataStoreProvider;
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public SettingsRepository get() {
    return provideSettingsRepository(settingsDataStoreProvider.get(), firebaseAuthProvider.get());
  }

  public static SettingsModule_ProvideSettingsRepositoryFactory create(
      Provider<SettingsDataStore> settingsDataStoreProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    return new SettingsModule_ProvideSettingsRepositoryFactory(settingsDataStoreProvider, firebaseAuthProvider);
  }

  public static SettingsRepository provideSettingsRepository(SettingsDataStore settingsDataStore,
      FirebaseAuth firebaseAuth) {
    return Preconditions.checkNotNullFromProvides(SettingsModule.INSTANCE.provideSettingsRepository(settingsDataStore, firebaseAuth));
  }
}
