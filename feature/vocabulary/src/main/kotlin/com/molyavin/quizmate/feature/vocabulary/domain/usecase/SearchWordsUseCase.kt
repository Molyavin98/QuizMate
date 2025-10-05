package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchWordsUseCase @Inject constructor(
    private val repository: VocabularyRepository
) {
    operator fun invoke(query: String): Flow<List<Word>> {
        return repository.searchWords(query)
    }
}
