package com.molyavin.quizmate.feature.vocabulary.domain.usecase;

/**
 * Use Case для імпорту слів з JSON файлу
 *
 * Приклад JSON:
 * [
 *  {
 *    "english": "hello",
 *    "ukrainian": "привіт",
 *    "example": "Hello, world!",
 *    "category": "greetings",
 *    "difficulty": "EASY"
 *  }
 * ]
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0003\u0016\u0017\u0018B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J0\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\bH\u0086B\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0012\u0010\u0011\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0002J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\r\u001a\u00020\bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0019"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/domain/usecase/ImportWordsFromJsonUseCase;", "", "repository", "Lcom/molyavin/quizmate/feature/vocabulary/domain/repository/VocabularyRepository;", "gson", "Lcom/google/gson/Gson;", "(Lcom/molyavin/quizmate/feature/vocabulary/domain/repository/VocabularyRepository;Lcom/google/gson/Gson;)V", "cleanIndexPrefix", "", "text", "invoke", "Lkotlin/Result;", "Lcom/molyavin/quizmate/feature/vocabulary/domain/usecase/ImportWordsFromJsonUseCase$ImportResult;", "jsonContent", "folderId", "invoke-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseDifficulty", "difficultyStr", "parseJson", "", "Lcom/molyavin/quizmate/feature/vocabulary/domain/usecase/ImportWordsFromJsonUseCase$WordData;", "ImportResult", "WordData", "WordsWrapper", "vocabulary_debug"})
public final class ImportWordsFromJsonUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    public ImportWordsFromJsonUseCase(@org.jetbrains.annotations.NotNull()
    com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository repository, @org.jetbrains.annotations.NotNull()
    com.google.gson.Gson gson) {
        super();
    }
    
    private final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase.WordData> parseJson(java.lang.String jsonContent) {
        return null;
    }
    
    private final java.lang.String parseDifficulty(java.lang.String difficultyStr) {
        return null;
    }
    
    /**
     * Видаляє індекси на початку і в кінці слова
     * Приклади:
     * - "1. hello" -> "hello"
     * - "hello10" -> "hello"
     * - "tree15" -> "tree"
     */
    private final java.lang.String cleanIndexPrefix(java.lang.String text) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J7\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\bH\u00d6\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u001a"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/domain/usecase/ImportWordsFromJsonUseCase$ImportResult;", "", "totalWords", "", "importedWords", "skippedWords", "errors", "", "", "(IIILjava/util/List;)V", "getErrors", "()Ljava/util/List;", "getImportedWords", "()I", "getSkippedWords", "getTotalWords", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "vocabulary_debug"})
    public static final class ImportResult {
        private final int totalWords = 0;
        private final int importedWords = 0;
        private final int skippedWords = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.String> errors = null;
        
        public ImportResult(int totalWords, int importedWords, int skippedWords, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> errors) {
            super();
        }
        
        public final int getTotalWords() {
            return 0;
        }
        
        public final int getImportedWords() {
            return 0;
        }
        
        public final int getSkippedWords() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getErrors() {
            return null;
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int component3() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase.ImportResult copy(int totalWords, int importedWords, int skippedWords, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> errors) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003JM\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b\u00a8\u0006\u001e"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/domain/usecase/ImportWordsFromJsonUseCase$WordData;", "", "english", "", "ukrainian", "example", "category", "difficulty", "imageUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "getDifficulty", "getEnglish", "getExample", "getImageUrl", "getUkrainian", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "vocabulary_debug"})
    static final class WordData {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String english = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String ukrainian = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String example = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String category = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String difficulty = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String imageUrl = null;
        
        public WordData(@org.jetbrains.annotations.NotNull()
        java.lang.String english, @org.jetbrains.annotations.NotNull()
        java.lang.String ukrainian, @org.jetbrains.annotations.Nullable()
        java.lang.String example, @org.jetbrains.annotations.Nullable()
        java.lang.String category, @org.jetbrains.annotations.Nullable()
        java.lang.String difficulty, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl) {
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
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getExample() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getCategory() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getDifficulty() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getImageUrl() {
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
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase.WordData copy(@org.jetbrains.annotations.NotNull()
        java.lang.String english, @org.jetbrains.annotations.NotNull()
        java.lang.String ukrainian, @org.jetbrains.annotations.Nullable()
        java.lang.String example, @org.jetbrains.annotations.Nullable()
        java.lang.String category, @org.jetbrains.annotations.Nullable()
        java.lang.String difficulty, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/domain/usecase/ImportWordsFromJsonUseCase$WordsWrapper;", "", "words", "", "Lcom/molyavin/quizmate/feature/vocabulary/domain/usecase/ImportWordsFromJsonUseCase$WordData;", "(Ljava/util/List;)V", "getWords", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "vocabulary_debug"})
    static final class WordsWrapper {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase.WordData> words = null;
        
        public WordsWrapper(@org.jetbrains.annotations.NotNull()
        java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase.WordData> words) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase.WordData> getWords() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase.WordData> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase.WordsWrapper copy(@org.jetbrains.annotations.NotNull()
        java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase.WordData> words) {
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