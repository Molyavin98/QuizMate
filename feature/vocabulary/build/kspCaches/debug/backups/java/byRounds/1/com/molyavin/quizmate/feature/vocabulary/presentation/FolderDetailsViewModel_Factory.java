package com.molyavin.quizmate.feature.vocabulary.presentation;

import androidx.lifecycle.SavedStateHandle;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllFoldersUseCase;
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
public final class FolderDetailsViewModel_Factory implements Factory<FolderDetailsViewModel> {
  private final Provider<GetAllFoldersUseCase> getAllFoldersUseCaseProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public FolderDetailsViewModel_Factory(Provider<GetAllFoldersUseCase> getAllFoldersUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.getAllFoldersUseCaseProvider = getAllFoldersUseCaseProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public FolderDetailsViewModel get() {
    return newInstance(getAllFoldersUseCaseProvider.get(), savedStateHandleProvider.get());
  }

  public static FolderDetailsViewModel_Factory create(
      Provider<GetAllFoldersUseCase> getAllFoldersUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new FolderDetailsViewModel_Factory(getAllFoldersUseCaseProvider, savedStateHandleProvider);
  }

  public static FolderDetailsViewModel newInstance(GetAllFoldersUseCase getAllFoldersUseCase,
      SavedStateHandle savedStateHandle) {
    return new FolderDetailsViewModel(getAllFoldersUseCase, savedStateHandle);
  }
}
