package com.annisaarss.movieapp.data.movie.model.response

import com.google.gson.annotations.SerializedName
import com.nbs.nucleosnucleo.Model

data class PopularItem(
    @SerializedName("page")
    val page:Int?,

    @SerializedName("results")
    val results:List<PopularDetailItem>?
) : Model()

data class PopularDetailItem(
    @SerializedName("poster_path")
    val posterPath:String?,
    @SerializedName("overview")
    val overview:String?,
    @SerializedName("release_date")
    val releaseDate:String?,
    @SerializedName("genre_ids")
    val genreId:List<Int>?,
    @SerializedName("id")
    val id:String?,
    @SerializedName("title")
    val title:String?,
    @SerializedName("backdrop_path")
    val backdropPath:String?,
    @SerializedName("popularity")
    val popularity:Int?,
)