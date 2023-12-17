package com.kumar.mdp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kumar.mdp.data.blogs
import com.kumar.mdp.model.Blog
import com.kumar.mdp.screen.BlogScreenUiState

class BlogViewModel: ViewModel() {

    private val _uiState : MutableLiveData<BlogScreenUiState> = MutableLiveData(BlogScreenUiState())
    val uiState: LiveData<BlogScreenUiState> = _uiState

    init {
        _uiState.value = _uiState.value?.copy(blogs = blogs)
    }

    fun addBlog(blog: Blog){
        blogs.add(blog)
        _uiState.value = _uiState.value?.copy(blogs = listOf())
        _uiState.value = _uiState.value?.copy(blogs = blogs)
    }
}