package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import javax.inject.Inject

class VocabularySyncFromFirestoreUseCase @Inject constructor(
    private val repository: VocabularyRepository
) {
    suspend operator fun invoke() {
        return repository.syncFromFirestore()
    }
}
