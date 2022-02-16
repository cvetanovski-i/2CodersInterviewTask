package com.ivancvetanovski.a2codersinterviewtask.data.repository

import android.accounts.NetworkErrorException
import com.ivancvetanovski.a2codersinterviewtask.data.net.Result
import com.ivancvetanovski.a2codersinterviewtask.data.net.service.ApiService
import com.ivancvetanovski.a2codersinterviewtask.data.net.service.RetrofitBuilder
import retrofit2.Response

abstract class BaseRepository {

  protected fun <T : ApiService> createApiService(service: Class<T>): ApiService =
    RetrofitBuilder.getRetrofitBuilder()
      .create(service)

  protected suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> =
    try {
      val myResp = call.invoke()
      if (myResp.isSuccessful) {
        Result.Success(myResp.body()!!)
      } else {
        Result.Failure(NetworkErrorException())
      }

    } catch (e: Exception) {
      Result.Failure(e)
    }
}