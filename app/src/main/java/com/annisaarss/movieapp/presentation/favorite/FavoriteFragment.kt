package com.annisaarss.movieapp.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.annisaarss.movieapp.R
import com.annisaarss.movieapp.databinding.FragmentFavoriteBinding
import com.nbs.nucleosnucleo.presentation.viewbinding.NucleoFragment

class FavoriteFragment : NucleoFragment<FragmentFavoriteBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater, container, false)
    }

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}
}