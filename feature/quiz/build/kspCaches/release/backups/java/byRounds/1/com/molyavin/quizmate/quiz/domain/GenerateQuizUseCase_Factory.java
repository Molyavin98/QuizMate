package com.molyavin.quizmate.quiz.domain;

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
public final class GenerateQuizUseCase_Factory implements Factory<GenerateQuizUseCase> {
  private final Provider<VocabularyRepository> vocabularyRepositoryProvider;

  public GenerateQuizUseCase_Factory(Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    this.vocabularyRepositoryProvider = vocabularyRepositoryProvider;
  }

  @Override
  public GenerateQuizUseCase get() {
    return newInstance(vocabularyRepositoryProvider.get());
  }

  public static GenerateQuizUseCase_Factory create(
      Provider<VocabularyRepository> vocabularyRepositoryProvider) {
    return new GenerateQuizUseCase_Factory(vocabularyRepositoryProvider);
  }

  public static GenerateQuizUseCase newInstance(VocabularyRepository vocabularyRepository) {
    return new GenerateQuizUseCase(vocabularyRepository);
  }
}
