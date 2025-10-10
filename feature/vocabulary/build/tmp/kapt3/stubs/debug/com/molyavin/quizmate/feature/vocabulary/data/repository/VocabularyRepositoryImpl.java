package com.molyavin.quizmate.feature.vocabulary.data.repository;

/**
 * Реалізація VocabularyRepository тільки з Firestore (без кешування)
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\tH\u0096@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u0018H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u001eH\u0016J\u0014\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\u001eH\u0016J\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\tH\u0096@\u00a2\u0006\u0002\u0010\u0019J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\f0\t2\u0006\u0010\"\u001a\u00020#H\u0096@\u00a2\u0006\u0002\u0010$J$\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0\t2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#H\u0096@\u00a2\u0006\u0002\u0010&J\u0018\u0010\'\u001a\u0004\u0018\u00010\f2\u0006\u0010(\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\u001e2\u0006\u0010*\u001a\u00020\u000eH\u0016J\u001c\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\u001e2\u0006\u0010\u001b\u001a\u00020\u000eH\u0016J\u0014\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\u001eH\u0016J\u001c\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\u001e2\u0006\u0010.\u001a\u00020\u000eH\u0016J\u000e\u0010/\u001a\u00020\u0018H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u001e\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u000203H\u0096@\u00a2\u0006\u0002\u00104J\u0016\u00105\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00066"}, d2 = {"Lcom/molyavin/quizmate/feature/vocabulary/data/repository/VocabularyRepositoryImpl;", "Lcom/molyavin/quizmate/feature/vocabulary/domain/repository/VocabularyRepository;", "firestoreDataSource", "Lcom/molyavin/quizmate/feature/vocabulary/data/local/VocabularyFirestoreDataSource;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/molyavin/quizmate/feature/vocabulary/data/local/VocabularyFirestoreDataSource;Lcom/google/firebase/auth/FirebaseAuth;)V", "foldersCache", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/molyavin/quizmate/feature/vocabulary/domain/model/VocabularyFolder;", "wordsCache", "Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;", "addWord", "", "word", "(Lcom/molyavin/quizmate/feature/vocabulary/domain/model/Word;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addWordsBatch", "words", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFolder", "name", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllWords", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFolder", "folderId", "deleteWord", "getAllFolders", "Lkotlinx/coroutines/flow/Flow;", "getAllWords", "getFavoriteWords", "getRandomWords", "count", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRandomWordsFromFolder", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordById", "id", "getWordsByCategory", "category", "getWordsByFolder", "getWordsWithoutFolder", "searchWords", "query", "syncFromFirestore", "updatePracticeStats", "wordId", "isCorrect", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateWord", "vocabulary_debug"})
public final class VocabularyRepositoryImpl implements com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFirestoreDataSource firestoreDataSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> wordsCache = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder>> foldersCache = null;
    
    public VocabularyRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFirestoreDataSource firestoreDataSource, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object syncFromFirestore(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> getAllWords() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getWordById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.molyavin.quizmate.feature.vocabulary.domain.model.Word> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> searchWords(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> getWordsByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getRandomWords(int count, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getRandomWordsFromFolder(@org.jetbrains.annotations.NotNull()
    java.lang.String folderId, int count, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getFavoriteWords(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addWord(@org.jetbrains.annotations.NotNull()
    com.molyavin.quizmate.feature.vocabulary.domain.model.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addWordsBatch(@org.jetbrains.annotations.NotNull()
    java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word> words, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateWord(@org.jetbrains.annotations.NotNull()
    com.molyavin.quizmate.feature.vocabulary.domain.model.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteWord(@org.jetbrains.annotations.NotNull()
    com.molyavin.quizmate.feature.vocabulary.domain.model.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updatePracticeStats(@org.jetbrains.annotations.NotNull()
    java.lang.String wordId, boolean isCorrect, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteAllWords(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> getWordsByFolder(@org.jetbrains.annotations.NotNull()
    java.lang.String folderId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.Word>> getWordsWithoutFolder() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder>> getAllFolders() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createFolder(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteFolder(@org.jetbrains.annotations.NotNull()
    java.lang.String folderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}