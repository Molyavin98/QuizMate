package com.molyavin.quizmate.feature.vocabulary.presentation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000e"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/FolderDetailsViewModel;", "Landroidx/lifecycle/ViewModel;", "getAllFoldersUseCase", "Lcom/molyavin/quizmate/feature/vocabulary/domain/usecase/GetAllFoldersUseCase;", "(Lcom/molyavin/quizmate/feature/vocabulary/domain/usecase/GetAllFoldersUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/FolderDetailsState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadFolders", "", "vocabulary_debug"})
public final class FolderDetailsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllFoldersUseCase getAllFoldersUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.molyavin.quizmate.feature.vocabulary.presentation.FolderDetailsState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.molyavin.quizmate.feature.vocabulary.presentation.FolderDetailsState> state = null;
    
    public FolderDetailsViewModel(@org.jetbrains.annotations.NotNull()
    com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllFoldersUseCase getAllFoldersUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.molyavin.quizmate.feature.vocabulary.presentation.FolderDetailsState> getState() {
        return null;
    }
    
    private final void loadFolders() {
    }
}