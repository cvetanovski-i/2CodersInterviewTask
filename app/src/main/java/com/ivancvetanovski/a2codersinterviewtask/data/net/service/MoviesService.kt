package com.ivancvetanovski.a2codersinterviewtask.data.net.service

import com.ivancvetanovski.a2codersinterviewtask.data.model.dto.MovieDto
import com.ivancvetanovski.a2codersinterviewtask.data.net.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService : ApiService {

  @GET("movie/popular")
  suspend fun loadPopularMovies(
    @Query("page") page: Int?,
    @Query("api_key") apiKey: String = "0370cfecfb8a5dfe60a861a8f6a43af3"
  ): Response<ApiResponse<MovieDto>>
}