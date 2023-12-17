package com.kumar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kumar.data.blogs
import com.kumar.model.Blog
import com.kumar.screen.BlogScreenUiState

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