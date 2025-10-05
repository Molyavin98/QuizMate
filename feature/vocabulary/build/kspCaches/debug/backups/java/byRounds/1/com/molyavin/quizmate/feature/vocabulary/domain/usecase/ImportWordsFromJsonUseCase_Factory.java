package com.molyavin.quizmate.feature.vocabulary.domain.usecase;

import com.google.gson.Gson;
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
public final class ImportWordsFromJsonUseCase_Factory implements Factory<ImportWordsFromJsonUseCase> {
  private final Provider<VocabularyRepository> repositoryProvider;

  private final Provider<Gson> gsonProvider;

  public ImportWordsFromJsonUseCase_Factory(Provider<VocabularyRepository> repositoryProvider,
      Provider<Gson> gsonProvider) {
    this.repositoryProvider = repositoryProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public ImportWordsFromJsonUseCase get() {
    return newInstance(repositoryProvider.get(), gsonProvider.get());
  }

  public static ImportWordsFromJsonUseCase_Factory create(
      Provider<VocabularyRepository> repositoryProvider, Provider<Gson> gsonProvider) {
    return new ImportWordsFromJsonUseCase_Factory(repositoryProvider, gsonProvider);
  }

  public static ImportWordsFromJsonUseCase newInstance(VocabularyRepository repository, Gson gson) {
    return new ImportWordsFromJsonUseCase(repository, gson);
  }
}
