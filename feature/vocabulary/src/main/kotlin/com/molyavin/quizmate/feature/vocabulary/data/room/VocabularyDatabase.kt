package com.molyavin.quizmate.feature.vocabulary.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFolderDao
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordDao
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFolderEntity
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordEntity

/**
 * Room Database для vocabulary модуля
 */
@Database(
    entities = [VocabularyWordEntity::class, VocabularyFolderEntity::class],
    version = 5,
    exportSchema = false
)
abstract class VocabularyDatabase : RoomDatabase() {
    abstract fun wordDao(): VocabularyWordDao
    abstract fun folderDao(): VocabularyFolderDao

    companion object {
        const val DATABASE_NAME = "vocabulary_database"

        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE words ADD COLUMN isLearned INTEGER NOT NULL DEFAULT 0")
                db.execSQL("ALTER TABLE words ADD COLUMN practiceCount INTEGER NOT NULL DEFAULT 0")
                db.execSQL("ALTER TABLE words ADD COLUMN firestoreId TEXT")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_words_firestoreId ON words(firestoreId)")
                db.execSQL("ALTER TABLE folders ADD COLUMN firestoreId TEXT")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_folders_firestoreId ON folders(firestoreId)")
            }
        }
    }
}