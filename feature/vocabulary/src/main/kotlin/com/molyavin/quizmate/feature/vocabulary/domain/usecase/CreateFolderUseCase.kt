package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import javax.inject.Inject

class CreateFolderUseCase @Inject constructor(
    private val repository: VocabularyRepository
) {
    suspend operator fun invoke(name: String): Result<String> {
        return try {
            if (name.isBlank()) {
                Result.failure(Exception("Folder name cannot be empty"))
            } else {
                val folderId = repository.createFolder(name.trim())
                Result.success(folderId)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
