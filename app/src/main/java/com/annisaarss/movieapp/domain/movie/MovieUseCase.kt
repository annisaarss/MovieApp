package com.annisaarss.movieapp.domain.movie

import com.annisaarss.movieapp.domain.movie.model.*
import io.reactivex.Single

interface MovieUseCase {
    fun getMostPopular(): Single<List<MostPopularDetail>>

    fun getCoomingSoon(): Single<List<CoomingSoonDetail>>

    fun getDetailMovie(id: String): Single<DetailMovieDetail>

    fun getPosterPopularMovie(): Single<List<PosterDetail>>

    fun getPosterCoomingSoonMovie(): Single<List<PosterDetail>>

    fun getBannerPopular(): Single<List<String>>

    fun searchMovie(expression: String): Single<List<MostPopularDetail>>
}