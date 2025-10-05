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
public final class CreateFolderUseCase_Factory implements Factory<CreateFolderUseCase> {
  private final Provider<VocabularyRepository> repositoryProvider;

  public CreateFolderUseCase_Factory(Provider<VocabularyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CreateFolderUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static CreateFolderUseCase_Factory create(
      Provider<VocabularyRepository> repositoryProvider) {
    return new CreateFolderUseCase_Factory(repositoryProvider);
  }

  public static CreateFolderUseCase newInstance(VocabularyRepository repository) {
    return new CreateFolderUseCase(repository);
  }
}
