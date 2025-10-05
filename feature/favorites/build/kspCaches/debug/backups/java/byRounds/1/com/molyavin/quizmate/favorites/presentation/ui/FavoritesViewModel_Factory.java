package com.molyavin.quizmate.favorites.presentation.ui;

import com.molyavin.quizmate.feature.vocabulary.data.local.WordDao;
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
  private final Provider<WordDao> wordDaoProvider;

  public FavoritesViewModel_Factory(Provider<WordDao> wordDaoProvider) {
    this.wordDaoProvider = wordDaoProvider;
  }

  @Override
  public FavoritesViewModel get() {
    return newInstance(wordDaoProvider.get());
  }

  public static FavoritesViewModel_Factory create(Provider<WordDao> wordDaoProvider) {
    return new FavoritesViewModel_Factory(wordDaoProvider);
  }

  public static FavoritesViewModel newInstance(WordDao wordDao) {
    return new FavoritesViewModel(wordDao);
  }
}
