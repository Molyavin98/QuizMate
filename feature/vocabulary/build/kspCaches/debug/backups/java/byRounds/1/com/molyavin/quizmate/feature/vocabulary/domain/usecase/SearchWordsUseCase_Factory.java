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
public final class SearchWordsUseCase_Factory implements Factory<SearchWordsUseCase> {
  private final Provider<VocabularyRepository> repositoryProvider;

  public SearchWordsUseCase_Factory(Provider<VocabularyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SearchWordsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SearchWordsUseCase_Factory create(
      Provider<VocabularyRepository> repositoryProvider) {
    return new SearchWordsUseCase_Factory(repositoryProvider);
  }

  public static SearchWordsUseCase newInstance(VocabularyRepository repository) {
    return new SearchWordsUseCase(repository);
  }
}
