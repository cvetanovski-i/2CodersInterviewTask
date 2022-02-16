package com.ivancvetanovski.a2codersinterviewtask.ui.movielist.data.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ivancvetanovski.a2codersinterviewtask.data.repository.MoviesRepository
import com.ivancvetanovski.a2codersinterviewtask.ui.movielist.data.MoviesSource
import com.ivancvetanovski.a2codersinterviewtask.ui.movielist.model.MovieListItem


class MovieListUseCase(private val moviesRepository: MoviesRepository = MoviesRepository()) {

  companion object {
    const val MOVIES_PAGE_SIZE = 20
  }

  fun getMovies(): Pager<Int, MovieListItem> = Pager(
    config = PagingConfig(MOVIES_PAGE_SIZE),
    pagingSourceFactory = { MoviesSource(moviesRepository) }
  )
}