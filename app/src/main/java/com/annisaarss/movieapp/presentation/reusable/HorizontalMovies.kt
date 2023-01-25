package com.annisaarss.movieapp.presentation.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annisaarss.movieapp.R
import com.annisaarss.movieapp.domain.movie.model.MostPopularDetail
import com.annisaarss.movieapp.domain.movie.model.PosterDetail
import com.annisaarss.movieapp.presentation.detail.DetailActivity
import com.annisaarss.movieapp.presentation.reusable.adapter.HorizontalMoviesAdapter
import com.nbs.nucleo.presentation.viewbinding.adapter.NucleoRecyclerAdapter

class HorizontalMovies constructor(
    context: Context,
    attrs: AttributeSet
) : LinearLayout(context, attrs) {

    init {
        val view = inflate(context, R.layout.layout_horizontal_movie, this)
        bindView(view)
    }

    private val horizontalMovieAdapter: HorizontalMoviesAdapter by lazy {
        HorizontalMoviesAdapter(
            context = context,
            items = mutableListOf(),
            onItemClicked = {
                OnItemStoryClicked(it)
            }
        )
    }

    private var tvKey: AppCompatTextView? = null
    private var rvMovie: RecyclerView? = null

    private var keyText: String? = null
    private var listMovie: List<PosterDetail>? = null

    private fun bindView(view: View) {
        tvKey = view.findViewById(R.id.tv_horizontal_movie)
        rvMovie = view.findViewById(R.id.rv_horizontal_movie)
    }

    fun setKeyText(text: String) {
        keyText = text
        tvKey?.text = keyText
    }

    fun setMovie(posterList: List<PosterDetail>) {
        listMovie = posterList

        rvMovie?.apply {
            adapter = horizontalMovieAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        horizontalMovieAdapter.clear()
        horizontalMovieAdapter.addOrUpdate(listMovie)
    }

    private fun OnItemStoryClicked(data: PosterDetail) {
        DetailActivity.start(context, idItem = data.id, titleMovie = data.title)
    }

}