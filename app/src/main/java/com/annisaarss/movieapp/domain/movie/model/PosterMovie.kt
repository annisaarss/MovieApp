package com.annisaarss.movieapp.domain.movie.model

import com.annisaarss.movieapp.data.movie.model.response.CoomingSoonDetailItem
import com.annisaarss.movieapp.data.movie.model.response.MostPopularDetailItem
import com.nbs.utils.emptyString

data class PosterDetail(
    val image: String = emptyString(),
    val id: String = emptyString(),
    val title: String = emptyString()
)

fun MostPopularDetailItem.mapToPosterDetail(): PosterDetail =
    PosterDetail(
        image = image.orEmpty(),
        id = id.orEmpty(),
        title = title.orEmpty()
    )

fun CoomingSoonDetailItem.mapToPosterDetail(): PosterDetail =
    PosterDetail(
        image = image.orEmpty(),
        id = id.orEmpty(),
        title = title.orEmpty()
    )

