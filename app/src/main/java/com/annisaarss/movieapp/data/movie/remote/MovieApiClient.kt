package com.annisaarss.movieapp.data.movie.remote

import com.annisaarss.movieapp.data.movie.model.response.CoomingSoonItem
import com.annisaarss.movieapp.data.movie.model.response.DetailMovieItem
import com.annisaarss.movieapp.data.movie.model.response.MostPopularItem
import com.annisaarss.movieapp.data.movie.model.response.SearchItem
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface MovieApiClient {
    @GET("MostPopularMovies/{apiKey}")
    fun getMostPopular(
        @Path("apiKey") apiKey: String): Single<Response<MostPopularItem>>

    @GET("ComingSoon/{apiKey}")
    fun getCoomingSoon(
        @Path("apiKey") apiKey: String): Single<Response<CoomingSoonItem>>

    @GET("Title/{apiKey}/{id}")
    fun getDetailMovie(
        @Path("apiKey") apiKey: String,
        @Path("id") id: String
    ): Single<Response<DetailMovieItem>>

    @GET("SearchMovie/{apiKey}/{expression}")
    fun search(
        @Path("apiKey") apiKey: String,
        @Path("expression") expression: String
    ): Single<Response<SearchItem>>
}