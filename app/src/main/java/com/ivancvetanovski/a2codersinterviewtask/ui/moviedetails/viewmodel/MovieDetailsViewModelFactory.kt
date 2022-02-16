package com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieDetailsViewModelFactory(private val movieId: Int) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
      return MovieDetailsViewModel(movieId) as T
    } else {
      throw IllegalArgumentException("Unknown ViewModel class")
    }
  }
}