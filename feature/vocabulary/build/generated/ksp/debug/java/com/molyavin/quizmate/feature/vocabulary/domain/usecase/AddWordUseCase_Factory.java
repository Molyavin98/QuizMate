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
public final class AddWordUseCase_Factory implements Factory<AddWordUseCase> {
  private final Provider<VocabularyRepository> repositoryProvider;

  public AddWordUseCase_Factory(Provider<VocabularyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddWordUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddWordUseCase_Factory create(Provider<VocabularyRepository> repositoryProvider) {
    return new AddWordUseCase_Factory(repositoryProvider);
  }

  public static AddWordUseCase newInstance(VocabularyRepository repository) {
    return new AddWordUseCase(repository);
  }
}
