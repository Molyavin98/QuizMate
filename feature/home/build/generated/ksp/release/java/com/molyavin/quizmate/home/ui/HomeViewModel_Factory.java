package com.molyavin.quizmate.home.ui;

import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllFoldersUseCase;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.VocabularySyncFromFirestoreUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetAllFoldersUseCase> getAllFoldersUseCaseProvider;

  private final Provider<VocabularySyncFromFirestoreUseCase> vocabularySyncFromFirestoreUseCaseProvider;

  public HomeViewModel_Factory(Provider<GetAllFoldersUseCase> getAllFoldersUseCaseProvider,
      Provider<VocabularySyncFromFirestoreUseCase> vocabularySyncFromFirestoreUseCaseProvider) {
    this.getAllFoldersUseCaseProvider = getAllFoldersUseCaseProvider;
    this.vocabularySyncFromFirestoreUseCaseProvider = vocabularySyncFromFirestoreUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getAllFoldersUseCaseProvider.get(), vocabularySyncFromFirestoreUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetAllFoldersUseCase> getAllFoldersUseCaseProvider,
      Provider<VocabularySyncFromFirestoreUseCase> vocabularySyncFromFirestoreUseCaseProvider) {
    return new HomeViewModel_Factory(getAllFoldersUseCaseProvider, vocabularySyncFromFirestoreUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetAllFoldersUseCase getAllFoldersUseCase,
      VocabularySyncFromFirestoreUseCase vocabularySyncFromFirestoreUseCase) {
    return new HomeViewModel(getAllFoldersUseCase, vocabularySyncFromFirestoreUseCase);
  }
}
