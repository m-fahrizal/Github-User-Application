package com.example.githubsubmission.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences (context: Context) {

    private val PREFS_NAME = "pref_darkMode"
    private var sharedPref: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun getValue(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }
}

//class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>){
//    private val THEME_KEY = booleanPreferencesKey("theme_setting")
//
//    fun getThemeSetting(): Flow<Boolean> {
//        return dataStore.data.map { preferences ->
//            preferences[THEME_KEY] ?: false
//        }
//    }
//
//    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
//        dataStore.edit { preferences ->
//            preferences[THEME_KEY] = isDarkModeActive
//        }
//    }
//
//    companion object {
//        @Volatile
//        private var INSTANCE: SettingPreferences? = null
//
//        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences {
//            return INSTANCE ?: synchronized(this) {
//                val instance = SettingPreferences(dataStore)
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}