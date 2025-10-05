package com.molyavin.quizmate.quiz.domain

import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import javax.inject.Inject
import kotlin.random.Random

/**
 * Use Case для генерації тесту з словника
 */
class GenerateQuizUseCase @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
) {
    suspend operator fun invoke(count: Int = 10): Result<List<QuizQuestion>> {
        return try {
            val words = vocabularyRepository.getRandomWords(count)

            if (words.isEmpty()) {
                return Result.failure(Exception("No words in dictionary. Please add some words first."))
            }

            val questions = words.map { word ->
                generateQuestion(word, words)
            }

            Result.success(questions)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun generateQuestion(word: Word, allWords: List<Word>): QuizQuestion {
        val questionType = if (Random.nextBoolean()) {
            QuestionType.TRANSLATE_TO_UKRAINIAN
        } else {
            QuestionType.TRANSLATE_TO_ENGLISH
        }

        val correctAnswer = when (questionType) {
            QuestionType.TRANSLATE_TO_UKRAINIAN -> word.ukrainian
            QuestionType.TRANSLATE_TO_ENGLISH -> word.english
        }

        // Get 3 wrong answers
        val wrongAnswers = allWords
            .filter { it.id != word.id }
            .shuffled()
            .take(3)
            .map { w ->
                when (questionType) {
                    QuestionType.TRANSLATE_TO_UKRAINIAN -> w.ukrainian
                    QuestionType.TRANSLATE_TO_ENGLISH -> w.english
                }
            }

        val options = (wrongAnswers + correctAnswer).shuffled()

        return QuizQuestion(
            word = word,
            questionType = questionType,
            options = options,
            correctAnswer = correctAnswer
        )
    }
}
