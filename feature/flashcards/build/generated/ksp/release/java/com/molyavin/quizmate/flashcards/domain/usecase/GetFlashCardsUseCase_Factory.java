package com.molyavin.quizmate.flashcards.domain.usecase;

import com.molyavin.quizmate.flashcards.domain.repository.FlashCardsRepository;
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
public final class GetFlashCardsUseCase_Factory implements Factory<GetFlashCardsUseCase> {
  private final Provider<FlashCardsRepository> repositoryProvider;

  public GetFlashCardsUseCase_Factory(Provider<FlashCardsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetFlashCardsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetFlashCardsUseCase_Factory create(
      Provider<FlashCardsRepository> repositoryProvider) {
    return new GetFlashCardsUseCase_Factory(repositoryProvider);
  }

  public static GetFlashCardsUseCase newInstance(FlashCardsRepository repository) {
    return new GetFlashCardsUseCase(repository);
  }
}
