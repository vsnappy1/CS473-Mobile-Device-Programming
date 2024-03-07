package com.kumar

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodiePalApplication : Application() {

    private val TAG = "FoodiePalApplication"
    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.Default).launch{
            saveInDataStore(stringPreferencesKey("name"), "Vishal Kumar")
            saveInDataStore(stringPreferencesKey("username"), "vk")
            saveInDataStore(stringPreferencesKey("password"), "2")
        }
    }

    private suspend fun saveInDataStore(key: Preferences.Key<String>, value: String){
        dataStore.edit { settings ->
            settings[key] = value
            Log.d(TAG, "saveInDataStore: ")
        }
    }

    fun getDataStoreInstance(): DataStore<Preferences> {
        return dataStore
    }
}