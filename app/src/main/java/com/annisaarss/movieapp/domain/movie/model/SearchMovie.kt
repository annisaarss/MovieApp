package com.annisaarss.movieapp.domain.movie.model

import com.annisaarss.movieapp.data.movie.model.response.MostPopularDetailItem
import com.annisaarss.movieapp.data.movie.model.response.SearchDetailItem
import com.nbs.utils.emptyString


fun SearchDetailItem.mapToMostPopularDetail(): MostPopularDetail =
    MostPopularDetail(
        image = image.orEmpty(),
        title = title.orEmpty(),
        id = id.orEmpty(),
        crew = description.orEmpty()
    )