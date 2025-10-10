package com.molyavin.quizmate.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "crashy_app_preferences")

class PreferencesManager(private val context: Context) {
    private val dataStore = context.dataStore

    companion object {
        val EXAMPLE_KEY = stringPreferencesKey("example_key")
    }

    val exampleFlow: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[EXAMPLE_KEY] ?: ""
        }

    suspend fun saveExample(value: String) {
        dataStore.edit { preferences ->
            preferences[EXAMPLE_KEY] = value
        }
    }
}