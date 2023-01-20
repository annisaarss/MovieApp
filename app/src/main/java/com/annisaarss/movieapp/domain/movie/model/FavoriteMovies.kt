package com.annisaarss.movieapp.domain.movie.model

import com.nbs.utils.emptyString

data class FavoriteDetail(
    val id: String = emptyString(),
    val image: String = emptyString(),
    val title: String = emptyString(),
    val year: String = emptyString(),
    val genre: String = emptyString()
)