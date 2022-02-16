package com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivancvetanovski.a2codersinterviewtask.data.net.Result
import com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.data.usecase.MovieDetailsUseCase
import com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.model.MovieDetails
import com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.model.mapToEntity
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
  private val movieId: Int,
  private val movieDetailsUseCase: MovieDetailsUseCase = MovieDetailsUseCase(movieId)
) : ViewModel() {

  private val movieDetailsLiveData: MutableLiveData<MovieDetails> = MutableLiveData()
  private val isLoadingInProgress: MutableLiveData<Boolean> = MutableLiveData(false)

  init {
    refreshData()
  }

  private fun refreshData() {
    loadMovieDetails()
  }

  private fun loadMovieDetails() {
    viewModelScope.launch {
      isLoadingInProgress.postValue(true)

      when (val response = movieDetailsUseCase.getMovieDetails()) {
        is Result.Success -> {
          movieDetailsLiveData.postValue(response.data.mapToEntity())
        }
        is Result.Failure -> {
          movieDetailsLiveData.postValue(null)
        }
      }

      isLoadingInProgress.postValue(false)
    }
  }

  fun getMovieDetails(): LiveData<MovieDetails> = movieDetailsLiveData

  fun getIsLoadingInProgress(): LiveData<Boolean> = isLoadingInProgress
}