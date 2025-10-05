package com.molyavin.quizmate.feature.vocabulary.di;

import android.content.Context;
import com.molyavin.quizmate.feature.vocabulary.data.room.VocabularyDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class VocabularyModule_Companion_ProvideVocabularyDatabaseFactory implements Factory<VocabularyDatabase> {
  private final Provider<Context> contextProvider;

  public VocabularyModule_Companion_ProvideVocabularyDatabaseFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public VocabularyDatabase get() {
    return provideVocabularyDatabase(contextProvider.get());
  }

  public static VocabularyModule_Companion_ProvideVocabularyDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new VocabularyModule_Companion_ProvideVocabularyDatabaseFactory(contextProvider);
  }

  public static VocabularyDatabase provideVocabularyDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(VocabularyModule.Companion.provideVocabularyDatabase(context));
  }
}
