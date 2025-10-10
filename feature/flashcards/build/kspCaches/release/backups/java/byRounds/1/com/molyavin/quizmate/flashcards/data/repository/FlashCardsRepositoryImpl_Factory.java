package com.molyavin.quizmate.flashcards.data.repository;

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
public final class FlashCardsRepositoryImpl_Factory implements Factory<FlashCardsRepositoryImpl> {
  private final Provider<VocabularyRepository> vocabularyRepositoryProvider;

  public FlashCardsRepositoryImpl_Factory(
      Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    this.vocabularyRepositoryProvider = vocabularyRepositoryProvider;
  }

  @Override
  public FlashCardsRepositoryImpl get() {
    return newInstance(vocabularyRepositoryProvider.get());
  }

  public static FlashCardsRepositoryImpl_Factory create(
      Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    return new FlashCardsRepositoryImpl_Factory(vocabularyRepositoryProvider);
  }

  public static FlashCardsRepositoryImpl newInstance(VocabularyRepository vocabularyRepository) {
    return new FlashCardsRepositoryImpl(vocabularyRepository);
  }
}
