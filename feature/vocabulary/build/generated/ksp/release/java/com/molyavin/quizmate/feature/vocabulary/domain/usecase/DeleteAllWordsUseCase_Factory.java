package com.molyavin.quizmate.feature.vocabulary.domain.usecase;

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
public final class DeleteAllWordsUseCase_Factory implements Factory<DeleteAllWordsUseCase> {
  private final Provider<VocabularyRepository> repositoryProvider;

  public DeleteAllWordsUseCase_Factory(Provider<VocabularyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteAllWordsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteAllWordsUseCase_Factory create(
      Provider<VocabularyRepository> repositoryProvider) {
    return new DeleteAllWordsUseCase_Factory(repositoryProvider);
  }

  public static DeleteAllWordsUseCase newInstance(VocabularyRepository repository) {
    return new DeleteAllWordsUseCase(repository);
  }
}
