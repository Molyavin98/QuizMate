package com.molyavin.quizmate.quiz.presentation;

import androidx.lifecycle.SavedStateHandle;
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository;
import com.molyavin.quizmate.quiz.domain.GenerateQuizUseCase;
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
public final class QuizViewModel_Factory implements Factory<QuizViewModel> {
  private final Provider<GenerateQuizUseCase> generateQuizUseCaseProvider;

  private final Provider<VocabularyRepository> vocabularyRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public QuizViewModel_Factory(Provider<GenerateQuizUseCase> generateQuizUseCaseProvider,
      Provider<VocabularyRepository> vocabularyRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.generateQuizUseCaseProvider = generateQuizUseCaseProvider;
    this.vocabularyRepositoryProvider = vocabularyRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public QuizViewModel get() {
    return newInstance(generateQuizUseCaseProvider.get(), vocabularyRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static QuizViewModel_Factory create(
      Provider<GenerateQuizUseCase> generateQuizUseCaseProvider,
      Provider<VocabularyRepository> vocabularyRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new QuizViewModel_Factory(generateQuizUseCaseProvider, vocabularyRepositoryProvider, savedStateHandleProvider);
  }

  public static QuizViewModel newInstance(GenerateQuizUseCase generateQuizUseCase,
      VocabularyRepository vocabularyRepository, SavedStateHandle savedStateHandle) {
    return new QuizViewModel(generateQuizUseCase, vocabularyRepository, savedStateHandle);
  }
}
