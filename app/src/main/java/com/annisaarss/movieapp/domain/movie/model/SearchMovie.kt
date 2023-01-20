package com.annisaarss.movieapp.domain.movie.model

import com.annisaarss.movieapp.data.movie.model.response.MostPopularDetailItem
import com.annisaarss.movieapp.data.movie.model.response.SearchDetailItem
import com.nbs.utils.emptyString

data class SearchDetail(
    val id: String = emptyString(),
    val resultType: String = emptyString(),
    val image: String = emptyString(),
    val title: String = emptyString(),
    val description: String = emptyString()
)

fun SearchDetailItem.mapToSearchDetail(): SearchDetail =
    SearchDetail(
        id = id.orEmpty(),
        resultType = resultType.orEmpty(),
        image = image.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty()
    )