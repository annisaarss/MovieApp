package com.annisaarss.movieapp.data.movie

import com.annisaarss.movieapp.data.movie.model.response.CoomingSoonItem
import com.annisaarss.movieapp.data.movie.model.response.DetailMovieItem
import com.annisaarss.movieapp.data.movie.model.response.MostPopularItem
import com.annisaarss.movieapp.data.movie.model.response.SearchItem
import com.nbs.nucleo.data.BaseRepository
import io.reactivex.Single

interface MovieRepository : BaseRepository {
    fun getMostPopular(): Single<MostPopularItem>

    fun getCoomingSoon(): Single<CoomingSoonItem>

    fun getDetailMovie(id: String): Single<DetailMovieItem>

    fun search(expression: String): Single<SearchItem>
}