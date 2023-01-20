package com.annisaarss.movieapp.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.annisaarss.movieapp.R
import com.nbs.nucleo.utils.extensions.setImageUrl
import java.util.*

class ViewPagerAdapter(val context: Context, val imageList: List<String>) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView: View = mLayoutInflater.inflate(R.layout.item_image_slider, container, false)

        val imageView: ImageView = itemView.findViewById<View>(R.id.imgBanner) as ImageView

        imageView.setImageUrl(context, imageList.get(position))

        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}