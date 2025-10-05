package com.molyavin.quizmate.feature.vocabulary.di;

import com.molyavin.quizmate.feature.vocabulary.data.local.WordDao;
import com.molyavin.quizmate.feature.vocabulary.data.room.VocabularyDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class VocabularyModule_Companion_ProvideWordDaoFactory implements Factory<WordDao> {
  private final Provider<VocabularyDatabase> databaseProvider;

  public VocabularyModule_Companion_ProvideWordDaoFactory(
      Provider<VocabularyDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public WordDao get() {
    return provideWordDao(databaseProvider.get());
  }

  public static VocabularyModule_Companion_ProvideWordDaoFactory create(
      Provider<VocabularyDatabase> databaseProvider) {
    return new VocabularyModule_Companion_ProvideWordDaoFactory(databaseProvider);
  }

  public static WordDao provideWordDao(VocabularyDatabase database) {
    return Preconditions.checkNotNullFromProvides(VocabularyModule.Companion.provideWordDao(database));
  }
}
