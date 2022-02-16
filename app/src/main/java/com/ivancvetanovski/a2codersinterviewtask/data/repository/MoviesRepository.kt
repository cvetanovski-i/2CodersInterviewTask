package com.ivancvetanovski.a2codersinterviewtask.data.repository

import com.ivancvetanovski.a2codersinterviewtask.data.model.dto.MovieDto
import com.ivancvetanovski.a2codersinterviewtask.data.net.ApiResponse
import com.ivancvetanovski.a2codersinterviewtask.data.net.Result
import com.ivancvetanovski.a2codersinterviewtask.data.net.service.MoviesService

class MoviesRepository : BaseRepository() {

  private val moviesService =
    createApiService(MoviesService::class.java) as MoviesService

  suspend fun getMovieList(page: Int?): Result<ApiResponse<MovieDto>> =
    safeApiCall {
      moviesService.loadPopularMovies(page)
    }
}