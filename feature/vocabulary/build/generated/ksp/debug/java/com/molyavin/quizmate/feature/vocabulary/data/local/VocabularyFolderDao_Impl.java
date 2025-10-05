package com.molyavin.quizmate.feature.vocabulary.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class VocabularyFolderDao_Impl implements VocabularyFolderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VocabularyFolderEntity> __insertionAdapterOfFolderEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFolder;

  public VocabularyFolderDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFolderEntity = new EntityInsertionAdapter<VocabularyFolderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `folders` (`id`,`name`,`createdAt`,`firestoreId`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VocabularyFolderEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindLong(3, entity.getCreatedAt());
        if (entity.getFirestoreId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFirestoreId());
        }
      }
    };
    this.__preparedStmtOfDeleteFolder = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM folders WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertFolder(final VocabularyFolderEntity folder,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFolderEntity.insertAndReturnId(folder);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFolder(final long folderId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFolder.acquire();
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
          __preparedStmtOfDeleteFolder.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<VocabularyFolderWithWordCountEntity>> getAllFoldersWithWordCount() {
    final String _sql = "\n"
            + "        SELECT f.*,\n"
            + "        (SELECT COUNT(*) FROM words WHERE folderId = f.id) as wordCount\n"
            + "        FROM folders f\n"
            + "        ORDER BY f.createdAt DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"words",
        "folders"}, new Callable<List<VocabularyFolderWithWordCountEntity>>() {
      @Override
      @NonNull
      public List<VocabularyFolderWithWordCountEntity> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
            final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
            final int _cursorIndexOfWordCount = CursorUtil.getColumnIndexOrThrow(_cursor, "wordCount");
            final List<VocabularyFolderWithWordCountEntity> _result = new ArrayList<VocabularyFolderWithWordCountEntity>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final VocabularyFolderWithWordCountEntity _item;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              final long _tmpCreatedAt;
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
              final String _tmpFirestoreId;
              if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
                _tmpFirestoreId = null;
              } else {
                _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
              }
              final int _tmpWordCount;
              _tmpWordCount = _cursor.getInt(_cursorIndexOfWordCount);
              _item = new VocabularyFolderWithWordCountEntity(_tmpId,_tmpName,_tmpCreatedAt,_tmpFirestoreId,_tmpWordCount);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<VocabularyFolderEntity>> getAllFolders() {
    final String _sql = "SELECT * FROM folders ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"folders"}, new Callable<List<VocabularyFolderEntity>>() {
      @Override
      @NonNull
      public List<VocabularyFolderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyFolderEntity> _result = new ArrayList<VocabularyFolderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyFolderEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyFolderEntity(_tmpId,_tmpName,_tmpCreatedAt,_tmpFirestoreId);
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
  public Object getAllFoldersSync(final Continuation<? super List<VocabularyFolderEntity>> $completion) {
    final String _sql = "SELECT * FROM folders ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<VocabularyFolderEntity>>() {
      @Override
      @NonNull
      public List<VocabularyFolderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final List<VocabularyFolderEntity> _result = new ArrayList<VocabularyFolderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VocabularyFolderEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _item = new VocabularyFolderEntity(_tmpId,_tmpName,_tmpCreatedAt,_tmpFirestoreId);
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
  public Object getFolderById(final long folderId,
      final Continuation<? super VocabularyFolderEntity> $completion) {
    final String _sql = "SELECT * FROM folders WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, folderId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VocabularyFolderEntity>() {
      @Override
      @Nullable
      public VocabularyFolderEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final VocabularyFolderEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _result = new VocabularyFolderEntity(_tmpId,_tmpName,_tmpCreatedAt,_tmpFirestoreId);
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
  public Object getFolderByFirestoreId(final String firestoreId,
      final Continuation<? super VocabularyFolderEntity> $completion) {
    final String _sql = "SELECT * FROM folders WHERE firestoreId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, firestoreId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VocabularyFolderEntity>() {
      @Override
      @Nullable
      public VocabularyFolderEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfFirestoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "firestoreId");
          final VocabularyFolderEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final String _tmpFirestoreId;
            if (_cursor.isNull(_cursorIndexOfFirestoreId)) {
              _tmpFirestoreId = null;
            } else {
              _tmpFirestoreId = _cursor.getString(_cursorIndexOfFirestoreId);
            }
            _result = new VocabularyFolderEntity(_tmpId,_tmpName,_tmpCreatedAt,_tmpFirestoreId);
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
  public Object getWordCountInFolder(final long folderId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM words WHERE folderId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, folderId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
