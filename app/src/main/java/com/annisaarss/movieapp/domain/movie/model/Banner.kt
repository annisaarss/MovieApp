package com.annisaarss.movieapp.domain.movie.model

import com.annisaarss.movieapp.data.movie.model.response.MostPopularDetailItem

fun MostPopularDetailItem.mapToImage(): String =
    image.orEmpty()