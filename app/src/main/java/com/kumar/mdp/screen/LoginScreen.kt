package com.kumar.mdp.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kumar.mdp.enums.LoginStatus
import com.kumar.mdp.R
import com.kumar.mdp.viewmodel.LoginScreenViewModel

data class LoginScreenUiState(
    val username: String = "",
    val password: String = "",
    val loginStatus: LoginStatus = LoginStatus.NOT_STARTED,
)

@Composable
fun LoginScreen(onUserLoggedIn: () -> Unit) {
    val viewModel: LoginScreenViewModel = viewModel()
    val uiState by viewModel.uiState.observeAsState(LoginScreenUiState())

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .size(200.dp),
                painter = painterResource(id = R.drawable.round_fastfood_24),
                contentDescription = "Image"
            )
            Spacer(modifier = Modifier.height(20.dp))
            UsernameField(
                value = uiState.username,
                onValueChange = { viewModel.onUsernameChange(it) },
            )
            PasswordField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChange(it) },
            )
            LoginButton(uiState.loginStatus) { viewModel.login() }
            Text(text = getLoginStatusText(uiState.loginStatus))
            if (uiState.loginStatus == LoginStatus.SUCCESSFUL) {
                onUserLoggedIn()
            }
        }
    }
}

@Composable
private fun LoginButton(
    loginStatus: LoginStatus,
    onLogin: () -> Unit
) {
    Button(
        modifier = Modifier
            .height(40.dp)
            .animateContentSize(),
        onClick = {
            if (loginStatus != LoginStatus.IN_PROGRESS) {
                onLogin()
            }
        }) {
        if (loginStatus == LoginStatus.IN_PROGRESS) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            Text(text = "Login")
        }
    }
}

@Composable
private fun UsernameField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(text = "Username") })
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        label = { Text(text = "Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Please provide localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        })
}

fun getLoginStatusText(loginStatus: LoginStatus): String {
    return when (loginStatus) {
        LoginStatus.NOT_STARTED -> ""
        LoginStatus.IN_PROGRESS -> ""
        LoginStatus.FAILURE -> "Invalid Credentials!!!"
        LoginStatus.SUCCESSFUL -> "Success"
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(onUserLoggedIn = {})
}