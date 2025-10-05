package com.molyavin.quizmate.feature.vocabulary.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VocabularyWordDao_Impl implements VocabularyWordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VocabularyWordEntity> __insertionAdapterOfWordEntity;

  private final EntityDeletionOrUpdateAdapter<VocabularyWordEntity> __deletionAdapterOfWordEntity;

  private final EntityDeletionOrUpdateAdapter<VocabularyWordEntity> __updateAdapterOfWordEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFavoriteStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllWords;

  private final SharedSQLiteStatement __preparedStmtOfDeleteWordsByFolder;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePracticeStats;

  public VocabularyWordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWordEntity = new EntityInsertionAdapter<VocabularyWordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `words` (`id`,`english`,`ukrainian`,`example`,`category`,`difficulty`,`imageUrl`,`folderId`,`createdAt`,`lastPracticed`,`correctCount`,`incorrectCount`,`isFavorite`,`isLearned`,`practiceCount`,`firestoreId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VocabularyWordEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getEnglish());
        statement.bindString(3, entity.getUkrainian());
        if (entity.getExample() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getExample());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCategory());
        }
        statement.bindString(6, entity.getDifficulty());
        if (entity.getImageUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageUrl());
        }
        if (entity.getFolderId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getFolderId());
        }
        statement.bindLong(9, entity.getCreatedAt());
        if (entity.getLastPracticed() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getLastPracticed());
        }
        statement.bindLong(11, entity.getCorrectCount());
        statement.bindLong(12, entity.getIncorrectCount());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(13, _tmp);
        final int _tmp_1 = entity.isLearned() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        statement.bindLong(15, entity.getPracticeCount());
        if (entity.getFirestoreId() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getFirestoreId());
        }
      }
    };
    this.__deletionAdapterOfWordEntity = new EntityDeletionOrUpdateAdapter<VocabularyWordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `words` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VocabularyWordEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfWordEntity = new EntityDeletionOrUpdateAdapter<VocabularyWordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `words` SET `id` = ?,`english` = ?,`ukrainian` = ?,`example` = ?,`category` = ?,`difficulty` = ?,`imageUrl` = ?,`folderId` = ?,`createdAt` = ?,`lastPracticed` = ?,`correctCount` = ?,`incorrectCount` = ?,`isFavorite` = ?,`isLearned` = ?,`practiceCount` = ?,`firestoreId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VocabularyWordEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getEnglish());
        statement.bindString(3, entity.getUkrainian());
        if (entity.getExample() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getExample());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCategory());
        }
        statement.bindString(6, entity.getDifficulty());
        if (entity.getImageUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageUrl());
        }
        if (entity.getFolderId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getFolderId());
        }
        statement.bindLong(9, entity.getCreatedAt());
        if (entity.getLastPracticed() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getLastPracticed());
        }
        statement.bindLong(11, entity.getCorrectCount());
        statement.bindLong(12, entity.getIncorrectCount());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(13, _tmp);
        final int _tmp_1 = entity.isLearned() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        statement.bindLong(15, entity.getPracticeCount());
        if (entity.getFirestoreId() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getFirestoreId());
        }
        statement.bindLong(17, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateFavoriteStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE words SET isFavorite = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllWords = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM words";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteWordsByFolder = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM words WHERE folderId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdatePracticeStats = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "\n"
                + "        UPDATE words\n"
                + "        SET correctCount = correctCount + ?,\n"
                + "            incorrectCount = incorrectCount + ?,\n"
                + "            lastPracticed = ?\n"
                + "        WHERE id = ?\n"
                + "    ";
        return _query;
      }
    };
  }

  @Override
  public Object insertWord(final VocabularyWordEntity word, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfWordEntity.insertAndReturnId(word);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteWord(final VocabularyWordEntity word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfWordEntity.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateWord(final VocabularyWordEntity word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWordEntity.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateFavoriteStatus(final long wordId, final boolean isFavorite,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFavoriteStatus.acquire();
        int _argIndex = 1;
        final int _tmp = isFavorite ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, wordId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateFavoriteStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllWords(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllWords.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllWords.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteWordsByFolder(final long folderId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteWordsByFolder.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, folderId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteWordsByFolder.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePracticeStats(final long wordId, final int incrementCorrect,
      final int incrementIncorrect, final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePracticeStats.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, incrementCorrect);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, incrementIncorrect);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 4;
        _stmt.bindLong(_argIndex, wordId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdatePracticeStats.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<VocabularyWordEntity>> getAllWords() {
    final String _sql = "SELECT * FROM words ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<VocabularyWordEntity>>() {
      @Override
      @NonNull
      public List<VocabularyWordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyWordEntity> _result = new ArrayList<VocabularyWordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyWordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAllWordsSync(final Continuation<? super List<VocabularyWordEntity>> $completion) {
    final String _sql = "SELECT * FROM words ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<VocabularyWordEntity>>() {
      @Override
      @NonNull
      public List<VocabularyWordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyWordEntity> _result = new ArrayList<VocabularyWordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyWordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getWordById(final long id, final Continuation<? super VocabularyWordEntity> $completion) {
    final String _sql = "SELECT * FROM words WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VocabularyWordEntity>() {
      @Override
      @Nullable
      public VocabularyWordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final VocabularyWordEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _result = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getWordByFirestoreId(final String firestoreId,
      final Continuation<? super VocabularyWordEntity> $completion) {
    final String _sql = "SELECT * FROM words WHERE firestoreId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, firestoreId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VocabularyWordEntity>() {
      @Override
      @Nullable
      public VocabularyWordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final VocabularyWordEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _result = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<VocabularyWordEntity>> searchWords(final String query) {
    final String _sql = "\n"
            + "        SELECT * FROM words\n"
            + "        WHERE english LIKE '%' || ? || '%'\n"
            + "        OR ukrainian LIKE '%' || ? || '%'\n"
            + "        ORDER BY createdAt DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<VocabularyWordEntity>>() {
      @Override
      @NonNull
      public List<VocabularyWordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyWordEntity> _result = new ArrayList<VocabularyWordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyWordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<VocabularyWordEntity>> getWordsByCategory(final String category) {
    final String _sql = "SELECT * FROM words WHERE category = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<VocabularyWordEntity>>() {
      @Override
      @NonNull
      public List<VocabularyWordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyWordEntity> _result = new ArrayList<VocabularyWordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyWordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getRandomWords(final int count,
      final Continuation<? super List<VocabularyWordEntity>> $completion) {
    final String _sql = "SELECT * FROM words ORDER BY RANDOM() LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, count);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<VocabularyWordEntity>>() {
      @Override
      @NonNull
      public List<VocabularyWordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyWordEntity> _result = new ArrayList<VocabularyWordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyWordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<VocabularyWordEntity>> getWordsByFolder(final long folderId) {
    final String _sql = "SELECT * FROM words WHERE folderId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, folderId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<VocabularyWordEntity>>() {
      @Override
      @NonNull
      public List<VocabularyWordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyWordEntity> _result = new ArrayList<VocabularyWordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyWordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getWordsByFolderSync(final long folderId,
      final Continuation<? super List<VocabularyWordEntity>> $completion) {
    final String _sql = "SELECT * FROM words WHERE folderId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, folderId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<VocabularyWordEntity>>() {
      @Override
      @NonNull
      public List<VocabularyWordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyWordEntity> _result = new ArrayList<VocabularyWordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyWordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<VocabularyWordEntity>> getWordsWithoutFolder() {
    final String _sql = "SELECT * FROM words WHERE folderId IS NULL ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<VocabularyWordEntity>>() {
      @Override
      @NonNull
      public List<VocabularyWordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyWordEntity> _result = new ArrayList<VocabularyWordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyWordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<VocabularyWordEntity>> getFavoriteWords() {
    final String _sql = "SELECT * FROM words WHERE isFavorite = 1 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<VocabularyWordEntity>>() {
      @Override
      @NonNull
      public List<VocabularyWordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfUkrainian = CursorUtil.getColumnIndexOrThrow(_cursor, "ukrainian");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folderId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastPracticed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPracticed");
          final int _cursorIndexOfCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctCount");
          final int _cursorIndexOfIncorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "incorrectCount");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final int _cursorIndexOfPracticeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceCount");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyWordEntity> _result = new ArrayList<VocabularyWordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyWordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpUkrainian;
            _tmpUkrainian = _cursor.getString(_cursorIndexOfUkrainian);
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpFolderId;
            if (_cursor.isNull(_cursorIndexOfFolderId)) {
              _tmpFolderId = null;
            } else {
              _tmpFolderId = _cursor.getLong(_cursorIndexOfFolderId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpLastPracticed;
            if (_cursor.isNull(_cursorIndexOfLastPracticed)) {
              _tmpLastPracticed = null;
            } else {
              _tmpLastPracticed = _cursor.getLong(_cursorIndexOfLastPracticed);
            }
            final int _tmpCorrectCount;
            _tmpCorrectCount = _cursor.getInt(_cursorIndexOfCorrectCount);
            final int _tmpIncorrectCount;
            _tmpIncorrectCount = _cursor.getInt(_cursorIndexOfIncorrectCount);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsLearned;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp_1 != 0;
            final int _tmpPracticeCount;
            _tmpPracticeCount = _cursor.getInt(_cursorIndexOfPracticeCount);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyWordEntity(_tmpId,_tmpEnglish,_tmpUkrainian,_tmpExample,_tmpCategory,_tmpDifficulty,_tmpImageUrl,_tmpFolderId,_tmpCreatedAt,_tmpLastPracticed,_tmpCorrectCount,_tmpIncorrectCount,_tmpIsFavorite,_tmpIsLearned,_tmpPracticeCount,_tmpFirestoreId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
