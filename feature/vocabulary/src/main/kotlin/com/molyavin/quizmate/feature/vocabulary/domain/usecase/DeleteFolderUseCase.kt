package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
class DeleteFolderUseCase(
    private val repository: VocabularyRepository
) {
    suspend operator fun invoke(folderId: String) {
        repository.deleteFolder(folderId)
    }
}
