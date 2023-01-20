package com.annisaarss.movieapp.domain.movie.model

import com.annisaarss.movieapp.data.movie.model.response.ActorListItem
import com.annisaarss.movieapp.data.movie.model.response.DetailMovieItem
import com.annisaarss.movieapp.data.movie.model.response.GenreListItem
import com.annisaarss.movieapp.data.movie.model.response.MostPopularDetailItem
import com.annisaarss.movieapp.databinding.ItemImageSliderBinding
import com.nbs.utils.emptyString
import com.nbs.utils.exts.orDefault

data class DetailMovieDetail(
    val year: String = emptyString(),
    val genreList: List<GenreDetail> = emptyList(),
    val title: String = emptyString(),
    val plot: String = emptyString(),
    val actorList: List<ActorDetail> = emptyList(),
    val id: String = emptyString(),
    val image: String = emptyString(),
    val duration: String = emptyString()

)

data class ActorDetail(
    val image: String = emptyString(),
    val name: String = emptyString()
)

data class GenreDetail(
    val key: String = emptyString(),
    val value: String = emptyString()
)

fun DetailMovieItem.mapToDetailMovieDetail(): DetailMovieDetail =
    DetailMovieDetail(
        year = year.orEmpty(),
        title = title.orEmpty(),
        plot = plot.orEmpty(),
        id = id.orEmpty(),
        image = image.orEmpty(),
        duration = duration.orEmpty(),
        actorList = actorList?.mapToListActorDetail().orEmpty(),
        genreList = genreList?.mapToListGenreDetail().orEmpty()
    )

fun ActorListItem.mapToActorDetail(): ActorDetail =
    ActorDetail(
        name = name.orEmpty(),
        image = image.orEmpty()
    )

fun List<ActorListItem>.mapToListActorDetail(): List<ActorDetail> =
    this.map { it.mapToActorDetail() }

fun GenreListItem.mapToGenreDetail(): GenreDetail =
    GenreDetail(
        key = key.orEmpty(),
        value = value.orEmpty()
    )

fun List<GenreListItem>.mapToListGenreDetail(): List<GenreDetail> =
    this.map { it.mapToGenreDetail() }