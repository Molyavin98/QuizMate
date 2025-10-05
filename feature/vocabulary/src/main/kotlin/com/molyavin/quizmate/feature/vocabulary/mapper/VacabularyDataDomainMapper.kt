package com.molyavin.quizmate.feature.vocabulary.mapper

import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFolderEntity
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFolderWithWordCountEntity
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordEntity
import com.molyavin.quizmate.feature.vocabulary.domain.model.Difficulty
import com.molyavin.quizmate.feature.vocabulary.domain.model.Folder
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import java.util.Date

fun VocabularyFolderEntity.toDomain(wordCount: Int = 0): Folder {
    return Folder(
        id = id,
        name = name,
        createdAt = Date(createdAt),
        wordCount = wordCount
    )
}

fun VocabularyFolderWithWordCountEntity.toDomain(): Folder {
    return Folder(
        id = id,
        name = name,
        createdAt = Date(createdAt),
        wordCount = wordCount
    )
}

fun VocabularyWordEntity.toDomain(): Word {
    return Word(
        id = id,
        english = english,
        ukrainian = ukrainian,
        example = example,
        category = category,
        difficulty = Difficulty.valueOf(difficulty),
        imageUrl = imageUrl,
        folderId = folderId,
        createdAt = Date(createdAt),
        lastPracticed = lastPracticed?.let { Date(it) },
        correctCount = correctCount,
        incorrectCount = incorrectCount,
        isFavorite = isFavorite,
        isLearned = isLearned,
        practiceCount = practiceCount
    )
}


