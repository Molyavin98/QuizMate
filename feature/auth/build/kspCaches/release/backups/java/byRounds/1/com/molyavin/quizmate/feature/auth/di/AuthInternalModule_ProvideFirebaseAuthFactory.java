package com.molyavin.quizmate.feature.auth.di;

import com.google.firebase.auth.FirebaseAuth;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class AuthInternalModule_ProvideFirebaseAuthFactory implements Factory<FirebaseAuth> {
  private final AuthInternalModule module;

  public AuthInternalModule_ProvideFirebaseAuthFactory(AuthInternalModule module) {
    this.module = module;
  }

  @Override
  public FirebaseAuth get() {
    return provideFirebaseAuth(module);
  }

  public static AuthInternalModule_ProvideFirebaseAuthFactory create(AuthInternalModule module) {
    return new AuthInternalModule_ProvideFirebaseAuthFactory(module);
  }

  public static FirebaseAuth provideFirebaseAuth(AuthInternalModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideFirebaseAuth());
  }
}
