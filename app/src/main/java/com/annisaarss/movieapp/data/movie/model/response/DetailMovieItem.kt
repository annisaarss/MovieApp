package com.annisaarss.movieapp.data.movie.model.response

import com.google.gson.annotations.SerializedName

data class DetailMovieItem(

	@field:SerializedName("year")
	val year: String? = null,

	@field:SerializedName("genreList")
	val genreList: List<GenreListItem>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("plot")
	val plot: String? = null,

	@field:SerializedName("actorList")
	val actorList: List<ActorListItem>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("runtimeStr")
	val duration: String? = null,
)

data class ActorListItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class GenreListItem(
	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("value")
	val value: String? = null
)
