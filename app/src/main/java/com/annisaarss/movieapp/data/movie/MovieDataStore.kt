package com.annisaarss.movieapp.data.movie

import com.annisaarss.movieapp.data.lib.getSingleApiError
import com.annisaarss.movieapp.data.movie.model.response.CoomingSoonItem
import com.annisaarss.movieapp.data.movie.model.response.DetailMovieItem
import com.annisaarss.movieapp.data.movie.model.response.MostPopularItem
import com.annisaarss.movieapp.data.movie.model.response.SearchItem
import com.annisaarss.movieapp.data.movie.remote.MovieApi
import com.annisaarss.movieapp.utils.constants.AppConstants
import com.nbs.nucleo.data.LocalDb
import com.nbs.nucleo.data.WebApi
import io.reactivex.Single

class MovieDataStore(
    api: MovieApi
) : MovieRepository {

    override val webService = api
    override fun getMostPopular(): Single<MostPopularItem> {
        return webService.getMostPopular(AppConstants.API_KEY)
            .lift(getSingleApiError())
            .map { it }
    }

    override fun getCoomingSoon(): Single<CoomingSoonItem> {
        return webService.getCoomingSoon(AppConstants.API_KEY)
            .lift(getSingleApiError())
            .map { it }
    }

    override fun getDetailMovie(id: String): Single<DetailMovieItem> {
        return webService.getDetailMovie(AppConstants.API_KEY, id)
            .lift(getSingleApiError())
            .map { it }
    }

    override fun search(expression: String): Single<SearchItem> {
        return webService.search(AppConstants.API_KEY, expression)
            .lift(getSingleApiError())
            .map { it }
    }


    override val dbService = null
}