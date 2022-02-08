package org.viroth.bookstore.app.networking.http

import com.google.gson.Gson
import okio.IOException
import org.viroth.bookstore.app.model.ErrorResponse
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultOf<T> {
    return try {
        val response = call.invoke()
        if (response.code() == 200) {
            ResultOf.Success(response.body()!!)
        } else {
            val errorResponse =
                Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
            ResultOf.Error(code = response.code(), error = errorResponse)
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultOf.NetworkError
            is HttpException -> {
                val code = throwable.code()
                ResultOf.Error(
                    code,
                    ErrorResponse(success = false, message = throwable.localizedMessage)
                )
            }
            else -> {
                ResultOf.Error(500, ErrorResponse(false, throwable.localizedMessage))
            }
        }
    }
}
