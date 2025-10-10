package com.molyavin.quizmate.feature.vocabulary.domain.repository;

/**
 * Repository interface для словника
 * Dependency Inversion Principle - Domain залежить від абстракції
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a6@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u000fH\u00a6@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\b0\u0015H&J\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0015H&J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a6@\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\u001a\u001a\u00020\u001bH\u00a6@\u00a2\u0006\u0002\u0010\u001cJ$\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u00a6@\u00a2\u0006\u0002\u0010\u001eJ\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00052\u0006\u0010 \u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u00152\u0006\u0010\"\u001a\u00020\u0003H&J\u001c\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u00152\u0006\u0010\u0012\u001a\u00020\u0003H&J\u0014\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0015H&J\u001c\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u00152\u0006\u0010&\u001a\u00020\u0003H&J\u000e\u0010\'\u001a\u00020\u000fH\u00a6@\u00a2\u0006\u0002\u0010\u0010J\u001e\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020+H\u00a6@\u00a2\u0006\u0002\u0010,J\u0016\u0010-\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006."}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/domain/repository/VocabularyRepository;", "", "addWord", "", "word", "Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;", "(Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addWordsBatch", "", "words", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFolder", "name", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllWords", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFolder", "folderId", "deleteWord", "getAllFolders", "Lkotlinx/coroutines/flow/Flow;", "Lcom/molyavin/quizmate/feature/vocabulary/domain/model/VocabularyFolder;", "getAllWords", "getFavoriteWords", "getRandomWords", "count", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRandomWordsFromFolder", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordById", "id", "getWordsByCategory", "category", "getWordsByFolder", "getWordsWithoutFolder", "searchWords", "query", "syncFromFirestore", "updatePracticeStats", "wordId", "isCorrect", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateWord", "vocabulary_debug"})
public abstract interface VocabularyRepository {
    
    /**
     * Отримати всі слова
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> getAllWords();
    
    /**
     * Отримати слово за ID
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getWordById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.molyavin.quizmate.feature.vocabulary.domain.model.Word> $completion);
    
    /**
     * Пошук слів
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> searchWords(@org.jetbrains.annotations.NotNull()
    java.lang.String query);
    
    /**
     * Отримати слова за категорією
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> getWordsByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category);
    
    /**
     * Отримати випадкові слова для тесту
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRandomWords(int count, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> $completion);
    
    /**
     * Отримати випадкові слова з папки для тесту
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRandomWordsFromFolder(@org.jetbrains.annotations.NotNull()
    java.lang.String folderId, int count, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> $completion);
    
    /**
     * Отримати обрані слова
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFavoriteWords(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> $completion);
    
    /**
     * Додати нове слово
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addWord(@org.jetbrains.annotations.NotNull()
    com.molyavin.quizmate.feature.vocabulary.domain.model.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    /**
     * Додати декілька слів одночасно (batch)
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addWordsBatch(@org.jetbrains.annotations.NotNull()
    java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word> words, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    /**
     * Оновити слово
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateWord(@org.jetbrains.annotations.NotNull()
    com.molyavin.quizmate.feature.vocabulary.domain.model.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Видалити слово
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteWord(@org.jetbrains.annotations.NotNull()
    com.molyavin.quizmate.feature.vocabulary.domain.model.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Оновити статистику практики
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePracticeStats(@org.jetbrains.annotations.NotNull()
    java.lang.String wordId, boolean isCorrect, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Видалити всі слова
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllWords(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Отримати слова в папці
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> getWordsByFolder(@org.jetbrains.annotations.NotNull()
    java.lang.String folderId);
    
    /**
     * Отримати слова без папки
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> getWordsWithoutFolder();
    
    /**
     * Отримати всі папки
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder>> getAllFolders();
    
    /**
     * Створити нову папку
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createFolder(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    /**
     * Видалити папку
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteFolder(@org.jetbrains.annotations.NotNull()
    java.lang.String folderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object syncFromFirestore(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}