package com.molyavin.quizmate.quiz.presentation;

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

  public QuizViewModel_Factory(Provider<GenerateQuizUseCase> generateQuizUseCaseProvider,
      Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    this.generateQuizUseCaseProvider = generateQuizUseCaseProvider;
    this.vocabularyRepositoryProvider = vocabularyRepositoryProvider;
  }

  @Override
  public QuizViewModel get() {
    return newInstance(generateQuizUseCaseProvider.get(), vocabularyRepositoryProvider.get());
  }

  public static QuizViewModel_Factory create(
      Provider<GenerateQuizUseCase> generateQuizUseCaseProvider,
      Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    return new QuizViewModel_Factory(generateQuizUseCaseProvider, vocabularyRepositoryProvider);
  }

  public static QuizViewModel newInstance(GenerateQuizUseCase generateQuizUseCase,
      VocabularyRepository vocabularyRepository) {
    return new QuizViewModel(generateQuizUseCase, vocabularyRepository);
  }
}
