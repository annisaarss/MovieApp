package com.annisaarss.movieapp.presentation.favorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.annisaarss.movieapp.databinding.ItemFavoriteBinding
import com.annisaarss.movieapp.databinding.ItemPopularBinding
import com.annisaarss.movieapp.domain.favorite.model.Favorite
import com.annisaarss.movieapp.domain.movie.model.FavoriteDetail
import com.annisaarss.movieapp.domain.movie.model.MostPopularDetail
import com.nbs.nucleo.presentation.adapter.OnItemClickListener
import com.nbs.nucleo.presentation.viewbinding.adapter.NucleoRecyclerAdapter
import com.nbs.nucleo.presentation.viewbinding.adapter.viewholder.NucleoItemViewHolder
import com.nbs.nucleo.utils.extensions.setImageUrl
import com.nbs.utils.exts.onClick

class FavoriteAdapter(
    private val context: Context,
    items: List<Favorite>,
    private val onItemClicked: ((Favorite) -> Unit)?
) : NucleoRecyclerAdapter<Favorite, FavoriteAdapter.FavoriteViewHolder>(context, items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(
            context,
            getViewBinding(parent, viewType),
            mItemClickListener
        )

    inner class FavoriteViewHolder(
        mContext: Context, binding: ViewBinding, mItemClickListener: OnItemClickListener?
    ) : NucleoItemViewHolder<Favorite>(
        mContext, binding, mItemClickListener, null
    ) {

        override fun bind(data: Favorite) {
            with(binding as ItemFavoriteBinding) {
                imgMovie.setImageUrl(context, data.image)
                tvTitleMovie.text = data.title
                tvGenreMovie.text = data.genre
                tvYearMovie.text = data.year
                btnFavorite.onClick {
                    onItemClicked?.apply {
                        invoke(data)
                    }
                }
            }
        }
    }

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }
}