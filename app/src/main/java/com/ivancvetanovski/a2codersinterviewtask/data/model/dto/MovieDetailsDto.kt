package com.ivancvetanovski.a2codersinterviewtask.data.model.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieDetailsDto(
  @SerializedName("id")
  val id: Int,

  @SerializedName("title")
  val title: String,

  @SerializedName("backdrop_path")
  val backdropPath: String?,

  @SerializedName("overview")
  val overview: String?,

  @SerializedName("poster_path")
  val posterPath: String?,

  @SerializedName("vote_average")
  val voteAverage: Float?,

  @SerializedName("release_date")
  val releaseDate: Date?
)