package com.annisaarss.movieapp.presentation.reusable.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.annisaarss.movieapp.databinding.ItemCoverBinding
import com.annisaarss.movieapp.domain.movie.model.MostPopularDetail
import com.annisaarss.movieapp.domain.movie.model.PosterDetail
import com.nbs.nucleo.presentation.adapter.OnItemClickListener
import com.nbs.nucleo.presentation.viewbinding.adapter.NucleoRecyclerAdapter
import com.nbs.nucleo.presentation.viewbinding.adapter.viewholder.NucleoItemViewHolder
import com.nbs.nucleo.utils.extensions.setImageUrl
import com.nbs.utils.exts.onClick

class HorizontalMoviesAdapter(
    private val context: Context,
    items: List<PosterDetail>,
    private val onItemClicked: ((PosterDetail) -> Unit)?
) : NucleoRecyclerAdapter<PosterDetail, HorizontalMoviesAdapter.PosterViewHolder>(context, items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder =
        PosterViewHolder(
            context,
            getViewBinding(parent, viewType),
            mItemClickListener
        )

    inner class PosterViewHolder(
        mContext: Context, binding: ViewBinding, mItemClickListener: OnItemClickListener?
    ) : NucleoItemViewHolder<PosterDetail>(
        mContext, binding, mItemClickListener, null
    ) {

        override fun bind(data: PosterDetail) {
            with(binding as ItemCoverBinding) {
                imgPoster.setImageUrl(context, data.image)

                root.onClick {
                    onItemClicked?.invoke(data)
                }
            }
        }
    }

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemCoverBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }
}