package com.annisaarss.movieapp.data.movie.model.response

import com.google.gson.annotations.SerializedName

data class SearchItem(
    @field:SerializedName("searchType")
    val searchType: String? = null,

    @field:SerializedName("expression")
    val expression: String? = null,

    @field:SerializedName("results")
    val results: List<SearchDetailItem>? = null,

    @field:SerializedName("errorMessage")
    val errorMessage: String? = null
)

data class SearchDetailItem(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("resultType")
    val resultType: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("description")
    val description: String? = null
)