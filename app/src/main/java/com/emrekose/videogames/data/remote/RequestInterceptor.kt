package com.emrekose.videogames.data.remote

import com.emrekose.videogames.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("x-rapidapi-key", Constants.API_KEY)
            .header("x-rapidapi-host", Constants.API_HOST)
            .build()
        return chain.proceed(request)
    }
}