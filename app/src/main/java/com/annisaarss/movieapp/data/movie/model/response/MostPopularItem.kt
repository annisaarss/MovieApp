package com.annisaarss.movieapp.data.movie.model.response

import com.google.gson.annotations.SerializedName

data class MostPopularItem(

    @field:SerializedName("items")
    val items: List<MostPopularDetailItem>? = null
)

data class MostPopularDetailItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("crew")
    val crew: String? = null
)
