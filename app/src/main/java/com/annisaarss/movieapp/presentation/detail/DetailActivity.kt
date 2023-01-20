package com.annisaarss.movieapp.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.annisaarss.movieapp.R
import com.annisaarss.movieapp.databinding.ActivityDetailBinding
import com.annisaarss.movieapp.utils.constants.BundleKeys
import com.nbs.nucleo.utils.emptyString
import com.nbs.nucleosnucleo.presentation.viewbinding.NucleoActivity
import com.nbs.utils.exts.getCompatColor

class DetailActivity : NucleoActivity<ActivityDetailBinding>() {

    companion object {
        fun start(context: Context, id: String?, titleMovie: String?) {
            val intent = Intent(context, DetailActivity::class.java).apply{
                putExtra(BundleKeys.EXTRA_ID, id)
                putExtra(BundleKeys.EXTRA_TITLE, titleMovie)
            }
            context.startActivity(intent)
        }
    }

    private lateinit var id: String
    private lateinit var titleMovie: String

    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        id = intent.getStringExtra(BundleKeys.EXTRA_ID) ?: emptyString()
        titleMovie = intent.getStringExtra(BundleKeys.EXTRA_TITLE) ?: emptyString()
    }

    override fun initUI() {

       binding.collapseToolbar.apply {
           title = titleMovie
           setCollapsedTitleTextColor(getCompatColor(R.color.white))
           setExpandedTitleColor(getCompatColor(R.color.transparant))
       }

//        binding.tvName.text = name
//        binding.tvDescription.text = description
//        binding.imgStory.setImageUrl(this, imageUrl)
    }

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}
}