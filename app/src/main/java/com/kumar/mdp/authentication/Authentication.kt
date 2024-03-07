package com.kumar.mdp.authentication

import com.kumar.mdp.data.users
import com.kumar.mdp.model.User
import kotlinx.coroutines.delay

class Authentication {

    companion object {

        suspend fun authenticate(username: String, password: String): User? {
            delay(2000) // Dummy delay to simulate web API call
            return users.firstOrNull { it.username == username && it.password == password }
        }
    }
}