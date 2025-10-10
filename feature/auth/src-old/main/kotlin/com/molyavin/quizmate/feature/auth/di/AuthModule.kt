package com.molyavin.quizmate.feature.auth.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [AuthInternalModule::class])
@InstallIn(SingletonComponent::class)
interface AuthModule

