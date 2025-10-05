package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFoldersUseCase @Inject constructor(
    private val repository: VocabularyRepository
) {
    operator fun invoke(): Flow<List<VocabularyFolder>> {
        return repository.getAllFolders()
    }
}
