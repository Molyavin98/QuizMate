package com.molyavin.quizmate.feature.vocabulary.presentation;

import com.molyavin.quizmate.feature.vocabulary.data.local.WordDao;
import com.molyavin.quizmate.feature.vocabulary.data.repository.VocabularyRepositoryImpl;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.AddWordUseCase;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.CreateFolderUseCase;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.DeleteAllWordsUseCase;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.DeleteFolderUseCase;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.DeleteWordUseCase;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllFoldersUseCase;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllWordsUseCase;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetWordsByFolderUseCase;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase;
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.SearchWordsUseCase;
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
public final class DictionaryViewModel_Factory implements Factory<DictionaryViewModel> {
  private final Provider<GetAllWordsUseCase> getAllWordsUseCaseProvider;

  private final Provider<AddWordUseCase> addWordUseCaseProvider;

  private final Provider<DeleteWordUseCase> deleteWordUseCaseProvider;

  private final Provider<SearchWordsUseCase> searchWordsUseCaseProvider;

  private final Provider<ImportWordsFromJsonUseCase> importWordsFromJsonUseCaseProvider;

  private final Provider<DeleteAllWordsUseCase> deleteAllWordsUseCaseProvider;

  private final Provider<GetAllFoldersUseCase> getAllFoldersUseCaseProvider;

  private final Provider<CreateFolderUseCase> createFolderUseCaseProvider;

  private final Provider<DeleteFolderUseCase> deleteFolderUseCaseProvider;

  private final Provider<GetWordsByFolderUseCase> getWordsByFolderUseCaseProvider;

  private final Provider<WordDao> wordDaoProvider;

  private final Provider<VocabularyRepositoryImpl> vocabularyRepositoryProvider;

  public DictionaryViewModel_Factory(Provider<GetAllWordsUseCase> getAllWordsUseCaseProvider,
      Provider<AddWordUseCase> addWordUseCaseProvider,
      Provider<DeleteWordUseCase> deleteWordUseCaseProvider,
      Provider<SearchWordsUseCase> searchWordsUseCaseProvider,
      Provider<ImportWordsFromJsonUseCase> importWordsFromJsonUseCaseProvider,
      Provider<DeleteAllWordsUseCase> deleteAllWordsUseCaseProvider,
      Provider<GetAllFoldersUseCase> getAllFoldersUseCaseProvider,
      Provider<CreateFolderUseCase> createFolderUseCaseProvider,
      Provider<DeleteFolderUseCase> deleteFolderUseCaseProvider,
      Provider<GetWordsByFolderUseCase> getWordsByFolderUseCaseProvider,
      Provider<WordDao> wordDaoProvider,
      Provider<VocabularyRepositoryImpl> vocabularyRepositoryProvider) {
    this.getAllWordsUseCaseProvider = getAllWordsUseCaseProvider;
    this.addWordUseCaseProvider = addWordUseCaseProvider;
    this.deleteWordUseCaseProvider = deleteWordUseCaseProvider;
    this.searchWordsUseCaseProvider = searchWordsUseCaseProvider;
    this.importWordsFromJsonUseCaseProvider = importWordsFromJsonUseCaseProvider;
    this.deleteAllWordsUseCaseProvider = deleteAllWordsUseCaseProvider;
    this.getAllFoldersUseCaseProvider = getAllFoldersUseCaseProvider;
    this.createFolderUseCaseProvider = createFolderUseCaseProvider;
    this.deleteFolderUseCaseProvider = deleteFolderUseCaseProvider;
    this.getWordsByFolderUseCaseProvider = getWordsByFolderUseCaseProvider;
    this.wordDaoProvider = wordDaoProvider;
    this.vocabularyRepositoryProvider = vocabularyRepositoryProvider;
  }

  @Override
  public DictionaryViewModel get() {
    return newInstance(getAllWordsUseCaseProvider.get(), addWordUseCaseProvider.get(), deleteWordUseCaseProvider.get(), searchWordsUseCaseProvider.get(), importWordsFromJsonUseCaseProvider.get(), deleteAllWordsUseCaseProvider.get(), getAllFoldersUseCaseProvider.get(), createFolderUseCaseProvider.get(), deleteFolderUseCaseProvider.get(), getWordsByFolderUseCaseProvider.get(), wordDaoProvider.get(), vocabularyRepositoryProvider.get());
  }

  public static DictionaryViewModel_Factory create(
      Provider<GetAllWordsUseCase> getAllWordsUseCaseProvider,
      Provider<AddWordUseCase> addWordUseCaseProvider,
      Provider<DeleteWordUseCase> deleteWordUseCaseProvider,
      Provider<SearchWordsUseCase> searchWordsUseCaseProvider,
      Provider<ImportWordsFromJsonUseCase> importWordsFromJsonUseCaseProvider,
      Provider<DeleteAllWordsUseCase> deleteAllWordsUseCaseProvider,
      Provider<GetAllFoldersUseCase> getAllFoldersUseCaseProvider,
      Provider<CreateFolderUseCase> createFolderUseCaseProvider,
      Provider<DeleteFolderUseCase> deleteFolderUseCaseProvider,
      Provider<GetWordsByFolderUseCase> getWordsByFolderUseCaseProvider,
      Provider<WordDao> wordDaoProvider,
      Provider<VocabularyRepositoryImpl> vocabularyRepositoryProvider) {
    return new DictionaryViewModel_Factory(getAllWordsUseCaseProvider, addWordUseCaseProvider, deleteWordUseCaseProvider, searchWordsUseCaseProvider, importWordsFromJsonUseCaseProvider, deleteAllWordsUseCaseProvider, getAllFoldersUseCaseProvider, createFolderUseCaseProvider, deleteFolderUseCaseProvider, getWordsByFolderUseCaseProvider, wordDaoProvider, vocabularyRepositoryProvider);
  }

  public static DictionaryViewModel newInstance(GetAllWordsUseCase getAllWordsUseCase,
      AddWordUseCase addWordUseCase, DeleteWordUseCase deleteWordUseCase,
      SearchWordsUseCase searchWordsUseCase, ImportWordsFromJsonUseCase importWordsFromJsonUseCase,
      DeleteAllWordsUseCase deleteAllWordsUseCase, GetAllFoldersUseCase getAllFoldersUseCase,
      CreateFolderUseCase createFolderUseCase, DeleteFolderUseCase deleteFolderUseCase,
      GetWordsByFolderUseCase getWordsByFolderUseCase, WordDao wordDao,
      VocabularyRepositoryImpl vocabularyRepository) {
    return new DictionaryViewModel(getAllWordsUseCase, addWordUseCase, deleteWordUseCase, searchWordsUseCase, importWordsFromJsonUseCase, deleteAllWordsUseCase, getAllFoldersUseCase, createFolderUseCase, deleteFolderUseCase, getWordsByFolderUseCase, wordDao, vocabularyRepository);
  }
}
