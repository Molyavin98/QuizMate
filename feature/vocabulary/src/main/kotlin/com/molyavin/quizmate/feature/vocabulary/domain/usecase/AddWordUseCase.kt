package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
class AddWordUseCase(
    private val repository: VocabularyRepository
) {
    suspend operator fun invoke(word: Word): Result<String> {
        return try {
            if (word.english.isBlank()) {
                return Result.failure(Exception("English word cannot be empty"))
            }
            if (word.ukrainian.isBlank()) {
                return Result.failure(Exception("Ukrainian translation cannot be empty"))
            }

            val id = repository.addWord(word)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
