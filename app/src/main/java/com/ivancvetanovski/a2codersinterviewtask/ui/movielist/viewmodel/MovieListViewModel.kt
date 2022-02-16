package com.ivancvetanovski.a2codersinterviewtask.ui.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.ivancvetanovski.a2codersinterviewtask.ui.movielist.data.usecase.MovieListUseCase
import com.ivancvetanovski.a2codersinterviewtask.ui.movielist.model.MovieListItem

class MovieListViewModel(private val movieListUseCase: MovieListUseCase = MovieListUseCase()) :
  ViewModel() {

  fun getMovies(): LiveData<PagingData<MovieListItem>> =
    movieListUseCase.getMovies().liveData.cachedIn(viewModelScope)
}