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
public final class GetWordsByFolderUseCase_Factory implements Factory<GetWordsByFolderUseCase> {
  private final Provider<VocabularyRepository> repositoryProvider;

  public GetWordsByFolderUseCase_Factory(Provider<VocabularyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetWordsByFolderUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetWordsByFolderUseCase_Factory create(
      Provider<VocabularyRepository> repositoryProvider) {
    return new GetWordsByFolderUseCase_Factory(repositoryProvider);
  }

  public static GetWordsByFolderUseCase newInstance(VocabularyRepository repository) {
    return new GetWordsByFolderUseCase(repository);
  }
}
