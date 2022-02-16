package com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.data.usecase

import com.ivancvetanovski.a2codersinterviewtask.data.model.dto.MovieDetailsDto
import com.ivancvetanovski.a2codersinterviewtask.data.net.Result
import com.ivancvetanovski.a2codersinterviewtask.data.repository.MoviesRepository

class MovieDetailsUseCase(
  private val movieId: Int,
  private val moviesRepository: MoviesRepository = MoviesRepository()
) {

  suspend fun getMovieDetails(): Result<MovieDetailsDto> =
    moviesRepository.getMovieDetails(movieId)

}