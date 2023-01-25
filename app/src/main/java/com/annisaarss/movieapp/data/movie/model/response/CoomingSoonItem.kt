package com.annisaarss.movieapp.data.movie.model.response

import com.google.gson.annotations.SerializedName

data class CoomingSoonItem(

    @field:SerializedName("items")
    val items: List<CoomingSoonDetailItem>? = null
)


data class CoomingSoonDetailItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("stars")
    val stars: String? = null
)

