package com.kumar.mdp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumar.mdp.authentication.Authentication
import com.kumar.mdp.enums.LoginStatus
import com.kumar.mdp.screen.LoginScreenUiState
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    private val _uiState: MutableLiveData<LoginScreenUiState> =
        MutableLiveData(LoginScreenUiState())
    val uiState: LiveData<LoginScreenUiState> = _uiState

    fun onUsernameChange(value: String) {
        _uiState.value = _uiState.value?.copy(
            username = value
        )
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value?.copy(
            password = value
        )
    }

    fun login() {
        viewModelScope.launch {
            _uiState.value = _uiState.value?.copy(loginStatus = LoginStatus.IN_PROGRESS)
            _uiState.value?.apply {
                val user = Authentication.authenticate(username, password)
                if (user != null) {
                    _uiState.value = this.copy(loginStatus = LoginStatus.SUCCESSFUL)
                } else {
                    _uiState.value = this.copy(loginStatus = LoginStatus.FAILURE)
                }
            }
        }
    }
}