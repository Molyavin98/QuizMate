package com.molyavin.quizmate.feature.vocabulary.presentation;

/**
 * MVI Contract для Dictionary екрану
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u00c7\u0002\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0006"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract;", "", "()V", "Effect", "Intent", "State", "vocabulary_debug"})
public final class DictionaryContract {
    @org.jetbrains.annotations.NotNull()
    public static final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract INSTANCE = null;
    
    private DictionaryContract() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Effect;", "", "ShowError", "ShowSuccess", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Effect$ShowError;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Effect$ShowSuccess;", "vocabulary_debug"})
    public static abstract interface Effect {
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Effect$ShowError;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Effect;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "vocabulary_debug"})
        public static final class ShowError implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Effect {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public ShowError(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Effect.ShowError copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Effect$ShowSuccess;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Effect;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "vocabulary_debug"})
        public static final class ShowSuccess implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Effect {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public ShowSuccess(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Effect.ShowSuccess copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0010\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0082\u0001\u0010\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\u00a8\u0006\""}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "", "AddWord", "CreateFolder", "DeleteAllWords", "DeleteFolder", "DeleteWord", "DismissWordDetails", "HideAddDialog", "HideAddFolderDialog", "ImportFromJson", "LoadWords", "SearchWords", "SelectFolder", "SelectWord", "ShowAddDialog", "ShowAddFolderDialog", "ToggleFavorite", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$AddWord;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$CreateFolder;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$DeleteAllWords;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$DeleteFolder;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$DeleteWord;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$DismissWordDetails;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$HideAddDialog;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$HideAddFolderDialog;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$ImportFromJson;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$LoadWords;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$SearchWords;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$SelectFolder;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$SelectWord;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$ShowAddDialog;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$ShowAddFolderDialog;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$ToggleFavorite;", "vocabulary_debug"})
    public static abstract interface Intent {
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003JQ\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u00a8\u0006\""}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$AddWord;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "english", "", "ukrainian", "example", "category", "difficulty", "imageUrl", "folderId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "getDifficulty", "getEnglish", "getExample", "getFolderId", "getImageUrl", "getUkrainian", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "", "toString", "vocabulary_debug"})
        public static final class AddWord implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String english = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String ukrainian = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String example = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String category = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String difficulty = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String imageUrl = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String folderId = null;
            
            public AddWord(@org.jetbrains.annotations.NotNull()
            java.lang.String english, @org.jetbrains.annotations.NotNull()
            java.lang.String ukrainian, @org.jetbrains.annotations.NotNull()
            java.lang.String example, @org.jetbrains.annotations.NotNull()
            java.lang.String category, @org.jetbrains.annotations.NotNull()
            java.lang.String difficulty, @org.jetbrains.annotations.NotNull()
            java.lang.String imageUrl, @org.jetbrains.annotations.Nullable()
            java.lang.String folderId) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getEnglish() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getUkrainian() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getExample() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getCategory() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getDifficulty() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getImageUrl() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getFolderId() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component5() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component6() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component7() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.AddWord copy(@org.jetbrains.annotations.NotNull()
            java.lang.String english, @org.jetbrains.annotations.NotNull()
            java.lang.String ukrainian, @org.jetbrains.annotations.NotNull()
            java.lang.String example, @org.jetbrains.annotations.NotNull()
            java.lang.String category, @org.jetbrains.annotations.NotNull()
            java.lang.String difficulty, @org.jetbrains.annotations.NotNull()
            java.lang.String imageUrl, @org.jetbrains.annotations.Nullable()
            java.lang.String folderId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$CreateFolder;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "vocabulary_debug"})
        public static final class CreateFolder implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String name = null;
            
            public CreateFolder(@org.jetbrains.annotations.NotNull()
            java.lang.String name) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getName() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.CreateFolder copy(@org.jetbrains.annotations.NotNull()
            java.lang.String name) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$DeleteAllWords;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "vocabulary_debug"})
        public static final class DeleteAllWords implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            public static final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.DeleteAllWords INSTANCE = null;
            
            private DeleteAllWords() {
                super();
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$DeleteFolder;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "folderId", "", "(Ljava/lang/String;)V", "getFolderId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "vocabulary_debug"})
        public static final class DeleteFolder implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String folderId = null;
            
            public DeleteFolder(@org.jetbrains.annotations.NotNull()
            java.lang.String folderId) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getFolderId() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.DeleteFolder copy(@org.jetbrains.annotations.NotNull()
            java.lang.String folderId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$DeleteWord;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "word", "Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;", "(Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;)V", "getWord", "()Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "vocabulary_debug"})
        public static final class DeleteWord implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            private final com.molyavin.quizmate.feature.vocabulary.domain.model.Word word = null;
            
            public DeleteWord(@org.jetbrains.annotations.NotNull()
            com.molyavin.quizmate.feature.vocabulary.domain.model.Word word) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.domain.model.Word getWord() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.domain.model.Word component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.DeleteWord copy(@org.jetbrains.annotations.NotNull()
            com.molyavin.quizmate.feature.vocabulary.domain.model.Word word) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$DismissWordDetails;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "vocabulary_debug"})
        public static final class DismissWordDetails implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            public static final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.DismissWordDetails INSTANCE = null;
            
            private DismissWordDetails() {
                super();
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$HideAddDialog;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "vocabulary_debug"})
        public static final class HideAddDialog implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            public static final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.HideAddDialog INSTANCE = null;
            
            private HideAddDialog() {
                super();
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$HideAddFolderDialog;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "vocabulary_debug"})
        public static final class HideAddFolderDialog implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            public static final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.HideAddFolderDialog INSTANCE = null;
            
            private HideAddFolderDialog() {
                super();
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$ImportFromJson;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "jsonContent", "", "folderId", "(Ljava/lang/String;Ljava/lang/String;)V", "getFolderId", "()Ljava/lang/String;", "getJsonContent", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "vocabulary_debug"})
        public static final class ImportFromJson implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String jsonContent = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String folderId = null;
            
            public ImportFromJson(@org.jetbrains.annotations.NotNull()
            java.lang.String jsonContent, @org.jetbrains.annotations.Nullable()
            java.lang.String folderId) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getJsonContent() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getFolderId() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.ImportFromJson copy(@org.jetbrains.annotations.NotNull()
            java.lang.String jsonContent, @org.jetbrains.annotations.Nullable()
            java.lang.String folderId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$LoadWords;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "vocabulary_debug"})
        public static final class LoadWords implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            public static final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.LoadWords INSTANCE = null;
            
            private LoadWords() {
                super();
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$SearchWords;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "query", "", "(Ljava/lang/String;)V", "getQuery", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "vocabulary_debug"})
        public static final class SearchWords implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String query = null;
            
            public SearchWords(@org.jetbrains.annotations.NotNull()
            java.lang.String query) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getQuery() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.SearchWords copy(@org.jetbrains.annotations.NotNull()
            java.lang.String query) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$SelectFolder;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "folderId", "", "(Ljava/lang/String;)V", "getFolderId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "vocabulary_debug"})
        public static final class SelectFolder implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String folderId = null;
            
            public SelectFolder(@org.jetbrains.annotations.Nullable()
            java.lang.String folderId) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getFolderId() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.SelectFolder copy(@org.jetbrains.annotations.Nullable()
            java.lang.String folderId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$SelectWord;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "word", "Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;", "(Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;)V", "getWord", "()Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "vocabulary_debug"})
        public static final class SelectWord implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            private final com.molyavin.quizmate.feature.vocabulary.domain.model.Word word = null;
            
            public SelectWord(@org.jetbrains.annotations.NotNull()
            com.molyavin.quizmate.feature.vocabulary.domain.model.Word word) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.domain.model.Word getWord() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.domain.model.Word component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.SelectWord copy(@org.jetbrains.annotations.NotNull()
            com.molyavin.quizmate.feature.vocabulary.domain.model.Word word) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$ShowAddDialog;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "vocabulary_debug"})
        public static final class ShowAddDialog implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            public static final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.ShowAddDialog INSTANCE = null;
            
            private ShowAddDialog() {
                super();
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$ShowAddFolderDialog;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "vocabulary_debug"})
        public static final class ShowAddFolderDialog implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            public static final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.ShowAddFolderDialog INSTANCE = null;
            
            private ShowAddFolderDialog() {
                super();
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent$ToggleFavorite;", "Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$Intent;", "wordId", "", "(Ljava/lang/String;)V", "getWordId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "vocabulary_debug"})
        public static final class ToggleFavorite implements com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String wordId = null;
            
            public ToggleFavorite(@org.jetbrains.annotations.NotNull()
            java.lang.String wordId) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getWordId() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.Intent.ToggleFavorite copy(@org.jetbrains.annotations.NotNull()
            java.lang.String wordId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b#\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B{\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\n\u0012\b\b\u0002\u0010\u000e\u001a\u00020\n\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\u0011J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u0010$\u001a\u00020\nH\u00c6\u0003J\t\u0010%\u001a\u00020\nH\u00c6\u0003J\t\u0010&\u001a\u00020\bH\u00c6\u0003J\t\u0010\'\u001a\u00020\nH\u00c6\u0003J\t\u0010(\u001a\u00020\nH\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003J\u007f\u0010*\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\bH\u00c6\u0001J\u0013\u0010+\u001a\u00020\n2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020.H\u00d6\u0001J\t\u0010/\u001a\u00020\bH\u00d6\u0001R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0017R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0017R\u0011\u0010\f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016\u00a8\u00060"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/presentation/DictionaryContract$State;", "", "words", "", "Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;", "vocabularyFolders", "Lcom/molyavin/quizmate/feature/vocabulary/domain/model/VocabularyFolder;", "selectedFolderId", "", "isLoading", "", "isImporting", "searchQuery", "showAddDialog", "showAddFolderDialog", "selectedWord", "error", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;ZZLjava/lang/String;ZZLcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "filteredWords", "getFilteredWords", "()Ljava/util/List;", "()Z", "getSearchQuery", "getSelectedFolderId", "getSelectedWord", "()Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;", "getShowAddDialog", "getShowAddFolderDialog", "getVocabularyFolders", "getWords", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "vocabulary_debug"})
    public static final class State {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word> words = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder> vocabularyFolders = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String selectedFolderId = null;
        private final boolean isLoading = false;
        private final boolean isImporting = false;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String searchQuery = null;
        private final boolean showAddDialog = false;
        private final boolean showAddFolderDialog = false;
        @org.jetbrains.annotations.Nullable()
        private final com.molyavin.quizmate.feature.vocabulary.domain.model.Word selectedWord = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String error = null;
        
        public State(@org.jetbrains.annotations.NotNull()
        java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word> words, @org.jetbrains.annotations.NotNull()
        java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder> vocabularyFolders, @org.jetbrains.annotations.Nullable()
        java.lang.String selectedFolderId, boolean isLoading, boolean isImporting, @org.jetbrains.annotations.NotNull()
        java.lang.String searchQuery, boolean showAddDialog, boolean showAddFolderDialog, @org.jetbrains.annotations.Nullable()
        com.molyavin.quizmate.feature.vocabulary.domain.model.Word selectedWord, @org.jetbrains.annotations.Nullable()
        java.lang.String error) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word> getWords() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder> getVocabularyFolders() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getSelectedFolderId() {
            return null;
        }
        
        public final boolean isLoading() {
            return false;
        }
        
        public final boolean isImporting() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSearchQuery() {
            return null;
        }
        
        public final boolean getShowAddDialog() {
            return false;
        }
        
        public final boolean getShowAddFolderDialog() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.molyavin.quizmate.feature.vocabulary.domain.model.Word getSelectedWord() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getError() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word> getFilteredWords() {
            return null;
        }
        
        public State() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component10() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean component5() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        public final boolean component7() {
            return false;
        }
        
        public final boolean component8() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.molyavin.quizmate.feature.vocabulary.domain.model.Word component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract.State copy(@org.jetbrains.annotations.NotNull()
        java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word> words, @org.jetbrains.annotations.NotNull()
        java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder> vocabularyFolders, @org.jetbrains.annotations.Nullable()
        java.lang.String selectedFolderId, boolean isLoading, boolean isImporting, @org.jetbrains.annotations.NotNull()
        java.lang.String searchQuery, boolean showAddDialog, boolean showAddFolderDialog, @org.jetbrains.annotations.Nullable()
        com.molyavin.quizmate.feature.vocabulary.domain.model.Word selectedWord, @org.jetbrains.annotations.Nullable()
        java.lang.String error) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}