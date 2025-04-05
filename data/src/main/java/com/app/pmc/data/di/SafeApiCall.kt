package com.app.pmc.data.di

import com.app.pmc.data.core.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface SafeApiCall {
    suspend fun <T> execute(apiCall: suspend () -> T): ApiResult<T>
}

class SafeApiCallImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SafeApiCall {
    override suspend fun <T> execute(apiCall: suspend () -> T): ApiResult<T> {
        return withContext(ioDispatcher) {
            try {
                val result = apiCall()
                ApiResult.Success(result)
            } catch (e: HttpException) {
                ApiResult.Failure.HttpError(
                    code = e.code(),
                    message = e.message(),
                    body = e.response()?.errorBody()?.string()
                )
            } catch (e: IOException) {
                ApiResult.Failure.NetworkError(e)
            } catch (e: Exception) {
                ApiResult.Failure.UnknownApiError(e)
            }
        }
    }
}