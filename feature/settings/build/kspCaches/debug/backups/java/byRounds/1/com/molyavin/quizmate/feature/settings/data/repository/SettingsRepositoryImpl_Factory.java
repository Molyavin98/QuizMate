package com.molyavin.quizmate.feature.settings.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.molyavin.quizmate.feature.settings.data.local.SettingsDataStore;
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
public final class SettingsRepositoryImpl_Factory implements Factory<SettingsRepositoryImpl> {
  private final Provider<SettingsDataStore> settingsDataStoreProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public SettingsRepositoryImpl_Factory(Provider<SettingsDataStore> settingsDataStoreProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    this.settingsDataStoreProvider = settingsDataStoreProvider;
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public SettingsRepositoryImpl get() {
    return newInstance(settingsDataStoreProvider.get(), firebaseAuthProvider.get());
  }

  public static SettingsRepositoryImpl_Factory create(
      Provider<SettingsDataStore> settingsDataStoreProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    return new SettingsRepositoryImpl_Factory(settingsDataStoreProvider, firebaseAuthProvider);
  }

  public static SettingsRepositoryImpl newInstance(SettingsDataStore settingsDataStore,
      FirebaseAuth firebaseAuth) {
    return new SettingsRepositoryImpl(settingsDataStore, firebaseAuth);
  }
}
