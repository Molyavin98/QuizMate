package com.molyavin.quizmate.splash;

import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository;
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
public final class SplashViewModel_Factory implements Factory<SplashViewModel> {
  private final Provider<VocabularyRepository> vocabularyRepositoryProvider;

  public SplashViewModel_Factory(Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    this.vocabularyRepositoryProvider = vocabularyRepositoryProvider;
  }

  @Override
  public SplashViewModel get() {
    return newInstance(vocabularyRepositoryProvider.get());
  }

  public static SplashViewModel_Factory create(
      Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    return new SplashViewModel_Factory(vocabularyRepositoryProvider);
  }

  public static SplashViewModel newInstance(VocabularyRepository vocabularyRepository) {
    return new SplashViewModel(vocabularyRepository);
  }
}
