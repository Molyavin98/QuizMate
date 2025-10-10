package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWordsByFolderUseCase @Inject constructor(
    private val repository: VocabularyRepository
) {
    operator fun invoke(folderId: String?): Flow<List<Word>> {
        return if (folderId == null) {
            repository.getAllWords()
        } else {
            repository.getWordsByFolder(folderId)
        }
    }
}
