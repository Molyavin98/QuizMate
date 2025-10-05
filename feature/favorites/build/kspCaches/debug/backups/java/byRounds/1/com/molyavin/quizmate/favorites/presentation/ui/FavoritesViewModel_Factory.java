package com.molyavin.quizmate.favorites.presentation.ui;

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
public final class FavoritesViewModel_Factory implements Factory<FavoritesViewModel> {
  private final Provider<VocabularyRepository> vocabularyRepositoryProvider;

  public FavoritesViewModel_Factory(Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    this.vocabularyRepositoryProvider = vocabularyRepositoryProvider;
  }

  @Override
  public FavoritesViewModel get() {
    return newInstance(vocabularyRepositoryProvider.get());
  }

  public static FavoritesViewModel_Factory create(
      Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    return new FavoritesViewModel_Factory(vocabularyRepositoryProvider);
  }

  public static FavoritesViewModel newInstance(VocabularyRepository vocabularyRepository) {
    return new FavoritesViewModel(vocabularyRepository);
  }
}
