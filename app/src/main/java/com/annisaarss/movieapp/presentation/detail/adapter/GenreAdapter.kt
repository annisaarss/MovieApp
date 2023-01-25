package com.annisaarss.movieapp.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.annisaarss.movieapp.databinding.ItemCastBinding
import com.annisaarss.movieapp.databinding.ItemGenreBinding
import com.annisaarss.movieapp.databinding.ItemGenresBinding
import com.annisaarss.movieapp.domain.movie.model.ActorDetail
import com.annisaarss.movieapp.domain.movie.model.GenreDetail
import com.nbs.nucleo.presentation.adapter.OnItemClickListener
import com.nbs.nucleo.presentation.viewbinding.adapter.NucleoRecyclerAdapter
import com.nbs.nucleo.presentation.viewbinding.adapter.viewholder.NucleoItemViewHolder
import com.nbs.nucleo.utils.extensions.setImageUrl

class GenreAdapter(
    private val context: Context,
    items: List<GenreDetail>
) : NucleoRecyclerAdapter<GenreDetail, GenreAdapter.GenreViewHolder>(context, items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(
            context,
            getViewBinding(parent, viewType),
            mItemClickListener
        )

    inner class GenreViewHolder(
        mContext: Context, binding: ViewBinding, mItemClickListener: OnItemClickListener?
    ) : NucleoItemViewHolder<GenreDetail>(
        mContext, binding, mItemClickListener, null
    ) {

        override fun bind(data: GenreDetail) {
            with(binding as ItemGenreBinding) {
                tvGenreMovie.text = data.value
                if (position == itemCount - 1) {
                    imgOval.isVisible = false
                }
            }
        }
    }

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemGenreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }
}