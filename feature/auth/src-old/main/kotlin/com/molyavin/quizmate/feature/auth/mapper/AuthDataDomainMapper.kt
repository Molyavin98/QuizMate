package com.molyavin.quizmate.feature.auth.mapper

import com.google.firebase.auth.FirebaseUser
import com.molyavin.quizmate.feature.auth.domain.model.User

fun FirebaseUser.toDomain(): User {
    return User(
        id = uid,
        email = email,
        displayName = displayName,
        photoUrl = photoUrl?.toString()
    )
}
