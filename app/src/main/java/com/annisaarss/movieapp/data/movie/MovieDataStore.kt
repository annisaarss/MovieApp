package com.annisaarss.movieapp.data.movie

import com.annisaarss.movieapp.data.movie.remote.MovieApi
import io.reactivex.Single

//class MovieDataStore(
//    api: MovieApi
//) : MovieRepository {
//
//    override val webService = api
//    override fun getTeamDetail(teamName: String): Single<TeamItem> {
//        return webService.getTeamDetail(teamName)
//            .lift(getSingleApiError())
//            .map { it }
//    }
//
//    override val dbService = null
//}