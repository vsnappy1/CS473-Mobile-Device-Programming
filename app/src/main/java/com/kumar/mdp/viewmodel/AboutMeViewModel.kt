package com.kumar.mdp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kumar.mdp.data.aboutMeList
import com.kumar.mdp.model.AboutMe
import com.kumar.mdp.screen.AboutMeScreenUiState

class AboutMeViewModel : ViewModel() {

    private val _uiState: MutableLiveData<AboutMeScreenUiState> =
        MutableLiveData(AboutMeScreenUiState())
    val uiState: LiveData<AboutMeScreenUiState> = _uiState

    init {
        // load recipes
        _uiState.value = _uiState.value?.copy(aboutMeList = aboutMeList)
    }

    fun addAboutMe(aboutMe: AboutMe) {
        aboutMeList.add(aboutMe)
        _uiState.value =
            _uiState.value?.copy(aboutMeList = listOf()) // list was not refreshing properly, so I added this
        _uiState.value = _uiState.value?.copy(aboutMeList = aboutMeList)
    }
}