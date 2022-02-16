package com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.model

import com.ivancvetanovski.a2codersinterviewtask.data.model.dto.MovieDetailsDto
import com.ivancvetanovski.a2codersinterviewtask.ui.util.extractYear
import com.ivancvetanovski.a2codersinterviewtask.ui.util.formatPosterUrl

data class MovieDetails(
  val id: Int,
  val name: String,
  val description: String?,
  val rating: Float?,
  val releaseYear: String?,
  val backdrop: String?,
  val poster: String?
)

/**
 * Maps a given [MovieDetailsDto] object to a [MovieDetails].
 *
 * @return [MovieDetails]
 */
fun MovieDetailsDto.mapToEntity() = MovieDetails(
  id = this.id,
  name = this.title,
  description = this.overview,
  rating = this.voteAverage,
  releaseYear = this.releaseDate.extractYear(),
  backdrop = this.backdropPath.formatPosterUrl(),
  poster = this.posterPath.formatPosterUrl()
)