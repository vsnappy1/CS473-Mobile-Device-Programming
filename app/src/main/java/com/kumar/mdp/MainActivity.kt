package com.kumar.mdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kumar.FoodiePalApplication
import com.kumar.data.users
import com.kumar.model.User
import com.kumar.screen.LoginScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
        getStoreUserAndInitialize()
    }

    private fun getStoreUserAndInitialize() {
        CoroutineScope(Dispatchers.Main).launch {
            val user = (application as FoodiePalApplication).getDataStoreInstance().data.map { preferences ->
                val name = preferences[stringPreferencesKey("name")] ?: ""
                val username = preferences[stringPreferencesKey("username")] ?: ""
                val password = preferences[stringPreferencesKey("password")] ?: ""
                User(name, username, password)
            }.first()
            users.add(user)
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