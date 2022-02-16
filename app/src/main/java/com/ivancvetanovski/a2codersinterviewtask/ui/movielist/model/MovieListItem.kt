package com.ivancvetanovski.a2codersinterviewtask.ui.movielist.model

import com.ivancvetanovski.a2codersinterviewtask.data.model.dto.MovieDto
import com.ivancvetanovski.a2codersinterviewtask.ui.util.formatPosterUrl

data class MovieListItem(
  val id: Int,
  val name: String,
  val description: String,
  val poster: String?
)

/**
 * Maps a given [MovieDto] object to a [MovieListItem].
 *
 * @return [MovieListItem]
 */
fun MovieDto.mapToEntity() = MovieListItem(
  id = this.id,
  name = this.title,
  description = this.overview,
  poster = this.posterPath.formatPosterUrl()
)