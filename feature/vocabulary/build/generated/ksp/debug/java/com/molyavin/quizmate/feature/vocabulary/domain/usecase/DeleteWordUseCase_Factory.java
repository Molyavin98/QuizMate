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
public final class DeleteWordUseCase_Factory implements Factory<DeleteWordUseCase> {
  private final Provider<VocabularyRepository> repositoryProvider;

  public DeleteWordUseCase_Factory(Provider<VocabularyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteWordUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteWordUseCase_Factory create(
      Provider<VocabularyRepository> repositoryProvider) {
    return new DeleteWordUseCase_Factory(repositoryProvider);
  }

  public static DeleteWordUseCase newInstance(VocabularyRepository repository) {
    return new DeleteWordUseCase(repository);
  }
}
