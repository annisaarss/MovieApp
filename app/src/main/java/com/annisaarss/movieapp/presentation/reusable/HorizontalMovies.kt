package com.annisaarss.movieapp.presentation.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.annisaarss.movieapp.R

class HorizontalMovies constructor(
    context: Context,
    attrs: AttributeSet
) : LinearLayout(context, attrs) {

    init {
        val view  = inflate(context, R.layout.layout_horizontal_movie, this)
        bindView(view)
    }

    private var tvKey: AppCompatTextView? = null
    private var rvMovie: RecyclerView? = null

    private var keyText: String? = null
    private var listMovie: List<String>? = null

    private fun bindView(view: View) {
        tvKey = view.findViewById(R.id.tv_horizontal_movie)
        rvMovie = view.findViewById(R.id.rv_horizontal_movie)
    }

    fun setKeyText(text: String) {
        keyText = text
        tvKey?.text = keyText
    }

    fun setMovie(urlPhoto: List<String>){
//        listMovie =
    }

}