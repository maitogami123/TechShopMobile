package com.vi.techshopmobile.data.mapper

import com.vi.techshopmobile.domain.model.ApiError
import com.vi.techshopmobile.domain.model.NetworkError
import retrofit2.HttpException
import java.io.IOException

//           Throwable                               NetworkError
//Map Either<Throwable, List<Product>> inside Either<NetworkError, List<Product>>

fun Throwable.toNetworkError(): NetworkError{
    val error = when(this){ //When the Exception we get
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }
    return NetworkError(
        error = error,
        t = this        //Throwable
    )
}