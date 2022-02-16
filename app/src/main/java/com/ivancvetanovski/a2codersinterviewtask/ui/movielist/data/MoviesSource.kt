package com.ivancvetanovski.a2codersinterviewtask.ui.movielist.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ivancvetanovski.a2codersinterviewtask.data.net.Result
import com.ivancvetanovski.a2codersinterviewtask.data.repository.MoviesRepository
import com.ivancvetanovski.a2codersinterviewtask.ui.movielist.model.MovieListItem
import com.ivancvetanovski.a2codersinterviewtask.ui.movielist.model.mapToEntity

class MoviesSource(
  private val moviesRepository: MoviesRepository = MoviesRepository()
) : PagingSource<Int, MovieListItem>() {

  override fun getRefreshKey(state: PagingState<Int, MovieListItem>): Int? = state.anchorPosition

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItem> {
    val nextPage = params.key ?: 1

    return when (val response = moviesRepository.getMovieList(nextPage)) {
      is Result.Success -> {
        LoadResult.Page(
          data = response.data.results.map { it.mapToEntity() },
          prevKey = if (nextPage == 1) null else nextPage - 1,
          nextKey = response.data.page + 1
        )
      }

      is Result.Failure -> {
        LoadResult.Error(
          Exception(response.error)
        )
      }
    }
  }
}