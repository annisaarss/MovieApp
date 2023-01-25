package com.annisaarss.movieapp.presentation.popular.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.annisaarss.movieapp.databinding.ItemPopularBinding
import com.annisaarss.movieapp.domain.movie.model.MostPopularDetail
import com.nbs.nucleo.presentation.adapter.OnItemClickListener
import com.nbs.nucleo.presentation.viewbinding.adapter.NucleoRecyclerAdapter
import com.nbs.nucleo.presentation.viewbinding.adapter.viewholder.NucleoItemViewHolder
import com.nbs.nucleo.utils.extensions.setImageUrl
import com.nbs.utils.exts.onClick

class PopularMoviesAdapter(
    private val context: Context,
    items: List<MostPopularDetail>,
    private val onItemClicked: ((MostPopularDetail) -> Unit)?
) : NucleoRecyclerAdapter<MostPopularDetail, PopularMoviesAdapter.PopularViewHolder>(
    context,
    items
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder =
        PopularViewHolder(
            context,
            getViewBinding(parent, viewType),
            mItemClickListener
        )

    inner class PopularViewHolder(
        mContext: Context, binding: ViewBinding, mItemClickListener: OnItemClickListener?
    ) : NucleoItemViewHolder<MostPopularDetail>(
        mContext, binding, mItemClickListener, null
    ) {

        override fun bind(data: MostPopularDetail) {
            with(binding as ItemPopularBinding) {
                imgCover.setImageUrl(context, data.image)
                tvTitleMovie.text = data.title
                tvDescriptionMovie.text = data.crew

                root.onClick {
                    onItemClicked?.invoke(data)
                }
            }
        }
    }

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemPopularBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }
}