import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCall<T : Any>(private val call: Call<T>) : Call<Result<T>> {

    override fun clone(): Call<Result<T>> = ResultCall(call.clone())

    override fun execute(): Response<Result<T>> {
        throw UnsupportedOperationException("ResultCall doesn't support execute")
    }

    override fun enqueue(callback: Callback<Result<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(Result.Success(response.body()))
                    )
                } else {
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(
                            Result.Failure(
                                response.code(),
                                response.errorBody()?.string()
                            )
                        )
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResponse = when (t) {
                    is IOException -> Result.NetworkError(t)
                    else -> Result.Unexpected(t)
                }
                callback.onResponse(this@ResultCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()
}

class ResultCallAdapter<R : Any>(private val responseType: Type) : CallAdapter<R, Call<Result<R>>> {
    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): Call<Result<R>> {
        TODO("Not yet implemented")
    }

    class Factory : CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (getRawType(returnType) != Call::class.java) {
                return null
            }

            check(returnType is ParameterizedType) {
                "return type must be parameterized as Call<Result<Foo>> or Call<Result<out Foo>>"
            }

            val responseType = getParameterUpperBound(0, returnType)

            if (getRawType(responseType) != Result::class.java) {
                return null
            }

            check(responseType is ParameterizedType) {
                "Response must be parameterized as Result<Foo> or Result<out Foo>"
            }

            val successBodyType = getParameterUpperBound(0, responseType)

            return ResultCallAdapter<Any>(successBodyType)
        }
    }
}

sealed class Result<out T : Any> {
    data class Success<T : Any>(val body: T?) : Result<T>()
    data class Failure(val code: Int, val error: String?) : Result<Nothing>()
    data class NetworkError(val exception: IOException) : Result<Nothing>()
    data class Unexpected(val t: Throwable?) : Result<Nothing>()
}