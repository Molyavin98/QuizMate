package com.molyavin.quizmate.favorites.presentation.ui;

import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordDao;
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
  private final Provider<VocabularyWordDao> wordDaoProvider;

  public FavoritesViewModel_Factory(Provider<VocabularyWordDao> wordDaoProvider) {
    this.wordDaoProvider = wordDaoProvider;
  }

  @Override
  public FavoritesViewModel get() {
    return newInstance(wordDaoProvider.get());
  }

  public static FavoritesViewModel_Factory create(Provider<VocabularyWordDao> wordDaoProvider) {
    return new FavoritesViewModel_Factory(wordDaoProvider);
  }

  public static FavoritesViewModel newInstance(VocabularyWordDao vocabularyWordDao) {
    return new FavoritesViewModel(vocabularyWordDao);
  }
}
