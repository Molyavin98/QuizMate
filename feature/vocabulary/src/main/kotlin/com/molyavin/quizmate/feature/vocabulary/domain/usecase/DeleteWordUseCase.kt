package com.molyavin.quizmate.feature.vocabulary.domain.usecase

import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import javax.inject.Inject

class DeleteWordUseCase @Inject constructor(
    private val repository: VocabularyRepository
) {
    suspend operator fun invoke(word: Word) {
        repository.deleteWord(word)
    }
}
