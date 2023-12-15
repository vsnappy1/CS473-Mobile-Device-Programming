package com.kumar.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kumar.enums.LoginStatus
import com.kumar.mdp.R
import com.kumar.viewmodel.LoginScreenViewModel

data class LoginScreenUiState(
    val username: String = "",
    val password: String = "",
    val loginStatus: LoginStatus = LoginStatus.NOT_STARTED,
)

@Composable
fun LoginScreen() {
    val viewModel: LoginScreenViewModel = viewModel()
    val uiState by viewModel.uiState.observeAsState(LoginScreenUiState())

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier
                .padding(top = 100.dp)
                .size(75.dp)
                .align(Alignment.TopCenter),
            painter = painterResource(id = R.drawable.round_fastfood_24),
            contentDescription = "Image"
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            OutlinedTextField(
                value = uiState.username,
                onValueChange = { viewModel.onUsernameChange(it) },
                label = { Text(text = "Username") })
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text(text = "Password") })
            Button(onClick = {
                viewModel.login()
            }) {
                if (uiState.loginStatus == LoginStatus.NOT_STARTED) {
                    Text(text = "Login")
                } else if (uiState.loginStatus == LoginStatus.IN_PROGRESS) {
                    CircularProgressIndicator()
                }
            }

            if (uiState.loginStatus == LoginStatus.SUCCESSFUL) {
                Text(text = "Success")
            }
            if (uiState.loginStatus == LoginStatus.FAILURE) {
                Text(text = "Failed")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}