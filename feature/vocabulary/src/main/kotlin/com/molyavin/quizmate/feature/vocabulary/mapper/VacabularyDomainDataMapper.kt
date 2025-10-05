package com.molyavin.quizmate.feature.vocabulary.mapper

import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFolderEntity
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordEntity
import com.molyavin.quizmate.feature.vocabulary.domain.model.Folder
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word

fun Folder.toEntity(): VocabularyFolderEntity {
    return VocabularyFolderEntity(
        id = id,
        name = name,
        createdAt = createdAt.time
    )
}

fun Word.toEntity(): VocabularyWordEntity {
    return VocabularyWordEntity(
        id = id,
        english = english,
        ukrainian = ukrainian,
        example = example,
        category = category,
        difficulty = difficulty.name,
        imageUrl = imageUrl,
        folderId = folderId,
        createdAt = createdAt.time,
        lastPracticed = lastPracticed?.time,
        correctCount = correctCount,
        incorrectCount = incorrectCount,
        isFavorite = isFavorite,
        isLearned = isLearned,
        practiceCount = practiceCount
    )
}