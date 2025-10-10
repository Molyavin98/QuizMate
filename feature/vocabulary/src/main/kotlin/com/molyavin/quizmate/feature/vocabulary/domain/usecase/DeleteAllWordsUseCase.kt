package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
class DeleteAllWordsUseCase(
    private val repository: VocabularyRepository
) {
    suspend operator fun invoke() {
        repository.deleteAllWords()
    }
}
