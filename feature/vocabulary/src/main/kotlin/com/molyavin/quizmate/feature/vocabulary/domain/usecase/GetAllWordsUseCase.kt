package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.Flow
class GetAllWordsUseCase(
    private val repository: VocabularyRepository
) {
    operator fun invoke(): Flow<List<Word>> {
        return repository.getAllWords()
    }
}
