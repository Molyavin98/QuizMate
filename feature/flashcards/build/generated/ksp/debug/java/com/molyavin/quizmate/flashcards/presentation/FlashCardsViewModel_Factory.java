package com.molyavin.quizmate.flashcards.presentation;

import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository;
import com.molyavin.quizmate.flashcards.domain.usecase.GetFlashCardsUseCase;
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
public final class FlashCardsViewModel_Factory implements Factory<FlashCardsViewModel> {
  private final Provider<GetFlashCardsUseCase> getFlashCardsUseCaseProvider;

  private final Provider<VocabularyRepository> vocabularyRepositoryProvider;

  public FlashCardsViewModel_Factory(Provider<GetFlashCardsUseCase> getFlashCardsUseCaseProvider,
      Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    this.getFlashCardsUseCaseProvider = getFlashCardsUseCaseProvider;
    this.vocabularyRepositoryProvider = vocabularyRepositoryProvider;
  }

  @Override
  public FlashCardsViewModel get() {
    return newInstance(getFlashCardsUseCaseProvider.get(), vocabularyRepositoryProvider.get());
  }

  public static FlashCardsViewModel_Factory create(
      Provider<GetFlashCardsUseCase> getFlashCardsUseCaseProvider,
      Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    return new FlashCardsViewModel_Factory(getFlashCardsUseCaseProvider, vocabularyRepositoryProvider);
  }

  public static FlashCardsViewModel newInstance(GetFlashCardsUseCase getFlashCardsUseCase,
      VocabularyRepository vocabularyRepository) {
    return new FlashCardsViewModel(getFlashCardsUseCase, vocabularyRepository);
  }
}
