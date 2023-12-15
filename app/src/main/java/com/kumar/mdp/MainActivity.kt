package com.kumar.mdp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kumar.data.users
import com.kumar.model.User
import com.kumar.screen.LoginScreen
import kotlinx.coroutines.flow.map

class MainActivity : AppCompatActivity() {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
        getStoreUserAndInitialize()
    }

    private fun getStoreUserAndInitialize() {
        dataStore.data.map { preferences ->
            val name = preferences[stringPreferencesKey("name")] ?: ""
            val username = preferences[stringPreferencesKey("username")] ?: ""
            val password = preferences[stringPreferencesKey("password")] ?: ""
            users.add(User(name, username, password))
        }
    }
}


@Composable
fun HelloWorld() {
    Text(text = "Hello World")
}

@Preview
@Composable
fun PreviewHelloWorld() {
    HelloWorld()
}