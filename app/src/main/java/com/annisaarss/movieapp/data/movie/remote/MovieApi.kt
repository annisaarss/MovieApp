package com.annisaarss.movieapp.data.movie.remote

import com.annisaarss.movieapp.data.movie.model.response.CoomingSoonItem
import com.annisaarss.movieapp.data.movie.model.response.DetailMovieItem
import com.annisaarss.movieapp.data.movie.model.response.MostPopularItem
import com.annisaarss.movieapp.data.movie.model.response.SearchItem
import com.nbs.nucleo.data.WebApi
import io.reactivex.Single
import retrofit2.Response
import kotlin.math.exp

class MovieApi(private val apiClient: MovieApiClient) : WebApi, MovieApiClient {

    override fun getMostPopular(apiKey: String): Single<Response<MostPopularItem>> {
        return apiClient.getMostPopular(apiKey)
    }

    override fun getCoomingSoon(apiKey: String): Single<Response<CoomingSoonItem>> {
        return apiClient.getCoomingSoon(apiKey)
    }

    override fun getDetailMovie(apiKey: String, id: String): Single<Response<DetailMovieItem>> {
        return apiClient.getDetailMovie(apiKey, id)
    }

    override fun search(apiKey: String, expression: String): Single<Response<SearchItem>> {
        return apiClient.search(apiKey, expression)
    }
}