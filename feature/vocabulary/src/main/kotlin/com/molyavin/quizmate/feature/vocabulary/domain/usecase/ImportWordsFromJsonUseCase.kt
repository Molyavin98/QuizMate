package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.first

/**
 * Use Case для імпорту слів з JSON файлу
 *
 * Приклад JSON:
 * [
 *   {
 *     "english": "hello",
 *     "ukrainian": "привіт",
 *     "example": "Hello, world!",
 *     "category": "greetings",
 *     "difficulty": "EASY"
 *   }
 * ]
 */
class ImportWordsFromJsonUseCase(
    private val repository: VocabularyRepository,
    private val gson: Gson
) {
    data class ImportResult(
        val totalWords: Int,
        val importedWords: Int,
        val skippedWords: Int,
        val errors: List<String>
    )

    suspend operator fun invoke(jsonContent: String, folderId: String? = null): Result<ImportResult> {
        return try {
            val words = parseJson(jsonContent)

            if (words.isEmpty()) {
                return Result.failure(Exception("No valid words found in JSON"))
            }

            // Видаляємо дублікати в JSON (за english.lowercase())
            val uniqueWords = words.distinctBy { it.english.trim().lowercase() }

            // Отримуємо існуючі слова з бази
            val existingWords = if (folderId != null) {
                repository.getWordsByFolder(folderId).first()
            } else {
                repository.getAllWords().first()
            }
            val existingEnglishWords = existingWords.map { it.english.lowercase() }.toSet()

            var skipped = 0
            val errors = mutableListOf<String>()
            val wordsToImport = mutableListOf<Word>()

            uniqueWords.forEachIndexed { index, wordData ->
                try {
                    if (wordData.english.isBlank() || wordData.ukrainian.isBlank()) {
                        skipped++
                        errors.add("Word at index $index: English or Ukrainian is empty")
                        return@forEachIndexed
                    }

                    val englishLower = wordData.english.trim().lowercase()

                    // Перевіряємо чи слово вже існує
                    if (existingEnglishWords.contains(englishLower)) {
                        skipped++
                        errors.add("Word '${wordData.english}' already exists, skipped")
                        return@forEachIndexed
                    }

                    val word = Word(
                        english = cleanIndexPrefix(wordData.english.trim()),
                        ukrainian = cleanIndexPrefix(wordData.ukrainian.trim()),
                        example = wordData.example?.trim(),
                        category = wordData.category?.trim(),
                        difficulty = parseDifficulty(wordData.difficulty),
                        imageUrl = wordData.imageUrl?.trim(),
                        folderId = folderId
                    )

                    // Додаємо слово до списку для batch імпорту
                    wordsToImport.add(word)
                } catch (e: Exception) {
                    skipped++
                    errors.add("Word at index $index: ${e.message}")
                }
            }

            // Зберігаємо всі слова одночасно через batch
            val imported = if (wordsToImport.isNotEmpty()) {
                try {
                    timber.log.Timber.d("Batch importing ${wordsToImport.size} words")
                    repository.addWordsBatch(wordsToImport)
                    wordsToImport.size
                } catch (e: Exception) {
                    timber.log.Timber.e(e, "Failed to batch import words")
                    errors.add("Batch import failed: ${e.message}")
                    0
                }
            } else {
                0
            }

            Result.success(
                ImportResult(
                    totalWords = words.size,
                    importedWords = imported,
                    skippedWords = skipped,
                    errors = errors
                )
            )
        } catch (e: JsonSyntaxException) {
            Result.failure(Exception("Invalid JSON format: ${e.message}"))
        } catch (e: Exception) {
            Result.failure(Exception("Import failed: ${e.message}"))
        }
    }

    private fun parseJson(jsonContent: String): List<WordData> {
        return try {
            // Try parsing as array
            val type = object : TypeToken<List<WordData>>() {}.type
            gson.fromJson(jsonContent, type)
        } catch (e: Exception) {
            // Try parsing as object with "words" array
            try {
                val wrapper = gson.fromJson(jsonContent, WordsWrapper::class.java)
                wrapper.words
            } catch (e2: Exception) {
                throw JsonSyntaxException("JSON must be array of words or object with 'words' array")
            }
        }
    }

    private fun parseDifficulty(difficultyStr: String?): String {
        return difficultyStr?.uppercase() ?: "MEDIUM"
    }

    /**
     * Видаляє індекси на початку і в кінці слова
     * Приклади:
     * - "1. hello" -> "hello"
     * - "hello10" -> "hello"
     * - "tree15" -> "tree"
     */
    private fun cleanIndexPrefix(text: String): String {
        return text
            .replace(Regex("^\\d+[.)\\s]+"), "") // Видаляє "1. " або "2) " на початку
            .replace(Regex("\\d+$"), "") // Видаляє цифри в кінці
            .trim()
    }

    private data class WordData(
        val english: String,
        val ukrainian: String,
        val example: String? = null,
        val category: String? = null,
        val difficulty: String? = null,
        val imageUrl: String? = null
    )

    private data class WordsWrapper(
        val words: List<WordData>
    )
}
