package com.molyavin.quizmate.flashcards.data.repository

import com.molyavin.quizmate.flashcards.domain.model.FlashCard
import com.molyavin.quizmate.flashcards.domain.repository.FlashCardsRepository
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Реалізація FlashCardsRepository
 * Використовує VocabularyRepository як джерело даних
 */
class FlashCardsRepositoryImpl(
    private val vocabularyRepository: VocabularyRepository
) : FlashCardsRepository {

    override fun getAllCards(): Flow<List<FlashCard>> {
        return vocabularyRepository.getAllWords().map { words ->
            words.map { word ->
                FlashCard(
                    id = word.id,
                    frontText = word.english,
                    backText = word.ukrainian,
                    example = word.example,
                    category = word.category,
                    difficulty = word.difficulty,
                    imageUrl = word.imageUrl,
                    isFavorite = word.isFavorite
                )
            }
        }
    }

    override fun getCardsByFolder(folderId: String): Flow<List<FlashCard>> {
        return vocabularyRepository.getWordsByFolder(folderId).map { words ->
            words.map { word ->
                FlashCard(
                    id = word.id,
                    frontText = word.english,
                    backText = word.ukrainian,
                    example = word.example,
                    category = word.category,
                    difficulty = word.difficulty,
                    imageUrl = word.imageUrl,
                    isFavorite = word.isFavorite
                )
            }
        }
    }

    override fun getFavoriteCards(): Flow<List<FlashCard>> {
        return vocabularyRepository.getAllWords().map { words ->
            words.filter { it.isFavorite }.map { word ->
                FlashCard(
                    id = word.id,
                    frontText = word.english,
                    backText = word.ukrainian,
                    example = word.example,
                    category = word.category,
                    difficulty = word.difficulty,
                    imageUrl = word.imageUrl,
                    isFavorite = word.isFavorite
                )
            }
        }
    }

    override fun getCardsByCategory(category: String): Flow<List<FlashCard>> {
        return vocabularyRepository.getAllWords().map { words ->
            words.filter { it.category == category }.map { word ->
                FlashCard(
                    id = word.id,
                    frontText = word.english,
                    backText = word.ukrainian,
                    example = word.example,
                    category = word.category,
                    difficulty = word.difficulty,
                    imageUrl = word.imageUrl,
                    isFavorite = word.isFavorite
                )
            }
        }
    }

    override fun getCardsByDifficulty(difficulty: String): Flow<List<FlashCard>> {
        return vocabularyRepository.getAllWords().map { words ->
            words.filter { it.difficulty == difficulty }.map { word ->
                FlashCard(
                    id = word.id,
                    frontText = word.english,
                    backText = word.ukrainian,
                    example = word.example,
                    category = word.category,
                    difficulty = word.difficulty,
                    imageUrl = word.imageUrl,
                    isFavorite = word.isFavorite
                )
            }
        }
    }

    override suspend fun loadCardsFromVocabulary() {
        // Картки автоматично завантажуються з vocabulary через Flow
    }
}
