package com.molyavin.quizmate.feature.vocabulary.ui.screen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a*\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001ah\u0010\u0007\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u000328\u0010\f\u001a4\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00010\rH\u0003\u001aN\u0010\u000e\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007\u001a\b\u0010\u0016\u001a\u00020\u0001H\u0003\u001a\u001e\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a:\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u00a8\u0006\u001e"}, d2 = {"AddFolderDialog", "", "onDismiss", "Lkotlin/Function0;", "onCreate", "Lkotlin/Function1;", "", "AddWordDialog", "vocabularyFolders", "", "Lcom/molyavin/quizmate/feature/vocabulary/domain/model/VocabularyFolder;", "selectedFolderId", "onAdd", "Lkotlin/Function7;", "DictionaryScreen", "onNavigateBack", "onNavigateToFolder", "folderId", "isLearningMode", "", "viewModel", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryViewModel;", "EmptyState", "WordDetailsDialog", "word", "Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;", "WordItem", "onClick", "onDelete", "onToggleFavorite", "vocabulary_debug"})
public final class DictionaryScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.material.ExperimentalMaterialApi.class, androidx.compose.foundation.ExperimentalFoundationApi.class})
    @androidx.compose.runtime.Composable()
    public static final void DictionaryScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNavigateToFolder, @org.jetbrains.annotations.Nullable()
    java.lang.String folderId, boolean isLearningMode, @org.jetbrains.annotations.NotNull()
    com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void WordItem(com.molyavin.quizmate.feature.vocabulary.domain.model.Word word, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, kotlin.jvm.functions.Function0<kotlin.Unit> onToggleFavorite) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyState() {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void AddWordDialog(java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder> vocabularyFolders, java.lang.String selectedFolderId, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function7<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onAdd) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void WordDetailsDialog(com.molyavin.quizmate.feature.vocabulary.domain.model.Word word, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AddFolderDialog(kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onCreate) {
    }
}