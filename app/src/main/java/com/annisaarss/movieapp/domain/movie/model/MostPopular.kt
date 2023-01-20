package com.annisaarss.movieapp.domain.movie.model

import com.annisaarss.movieapp.data.movie.model.response.MostPopularDetailItem
import com.nbs.utils.emptyString

data class MostPopularDetail(
    val image: String = emptyString(),
    val id: String = emptyString(),
    val title: String = emptyString(),
    val crew: String = emptyString()
)

fun MostPopularDetailItem.mapToMostPopularDetail(): MostPopularDetail =
    MostPopularDetail(
        image = image.orEmpty(),
        title = title.orEmpty(),
        id = id.orEmpty(),
        crew = crew.orEmpty()
    )