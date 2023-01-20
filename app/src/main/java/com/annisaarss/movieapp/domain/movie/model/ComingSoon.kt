package com.annisaarss.movieapp.domain.movie.model

import com.annisaarss.movieapp.data.movie.model.response.CoomingSoonDetailItem
import com.annisaarss.movieapp.data.movie.model.response.MostPopularDetailItem
import com.nbs.utils.emptyString

data class CoomingSoonDetail(
    val image: String = emptyString(),
    val id: String = emptyString(),
    val title: String = emptyString(),
    val stars: String = emptyString()
)

fun CoomingSoonDetailItem.mapToCoomingSoonDetail(): CoomingSoonDetail =
    CoomingSoonDetail(
        image = image.orEmpty(),
        title = title.orEmpty(),
        id = id.orEmpty(),
        stars = stars.orEmpty()
    )