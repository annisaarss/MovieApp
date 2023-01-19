package com.annisaarss.movieapp.data.movie.remote

import com.annisaarss.movieapp.data.movie.model.response.PopularItem
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AddStoryApiClient {
    @GET("/movie/popular")
    fun getPopular(
        @Query("api_key") apiKey: String
    ): Single<Response<PopularItem>>
}