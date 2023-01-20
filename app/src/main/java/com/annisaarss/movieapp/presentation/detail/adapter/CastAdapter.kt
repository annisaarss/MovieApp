package com.annisaarss.movieapp.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.annisaarss.movieapp.databinding.ItemCastBinding
import com.annisaarss.movieapp.databinding.ItemPopularBinding
import com.annisaarss.movieapp.domain.movie.model.ActorDetail
import com.annisaarss.movieapp.domain.movie.model.MostPopularDetail
import com.bumptech.glide.Glide
import com.nbs.nucleo.presentation.adapter.OnItemClickListener
import com.nbs.nucleo.presentation.viewbinding.adapter.NucleoRecyclerAdapter
import com.nbs.nucleo.presentation.viewbinding.adapter.viewholder.NucleoItemViewHolder
import com.nbs.nucleo.utils.extensions.setImageUrl
import com.nbs.utils.exts.onClick

class CastAdapter(
    private val context: Context,
    items: List<ActorDetail>
) : NucleoRecyclerAdapter<ActorDetail, CastAdapter.CastViewHolder>(context, items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder(
            context,
            getViewBinding(parent, viewType),
            mItemClickListener
        )

    inner class CastViewHolder(
        mContext: Context, binding: ViewBinding, mItemClickListener: OnItemClickListener?
    ) : NucleoItemViewHolder<ActorDetail>(
        mContext, binding, mItemClickListener, null
    ) {

        override fun bind(data: ActorDetail) {
            with(binding as ItemCastBinding) {
                imgCast.setImageUrl(context, data.image)
//                Glide.with(context)
//                    .load(data.image)
//                    .circleCrop()
//                    .into(imgCast)
                tvNameCast.text = data.name
            }
        }
    }

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemCastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }
}