package com.ivancvetanovski.a2codersinterviewtask.data.net.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Builder for the [Retrofit] instance.
 */
object RetrofitBuilder {

  private const val API_URL = "https://api.themoviedb.org/3/"

  /**
   * Builder that returns a [Retrofit] instance with [OkHttpClient] and [GsonConverterFactory].
   *
   * @return [Retrofit]
   */
  fun getRetrofitBuilder(): Retrofit = Retrofit.Builder()
    .baseUrl(API_URL)
    .client(getOkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  private fun getInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }

  private fun getOkHttpClient() = OkHttpClient().newBuilder()
    .addInterceptor(getInterceptor())
    .build()
}