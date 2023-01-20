package com.annisaarss.movieapp.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.annisaarss.movieapp.R
import com.annisaarss.movieapp.databinding.FragmentFavoriteBinding
import com.annisaarss.movieapp.domain.movie.model.FavoriteDetail
import com.annisaarss.movieapp.presentation.favorite.adapter.FavoriteAdapter
import com.annisaarss.movieapp.presentation.popular.adapter.PopularMoviesAdapter
import com.annisaarss.movieapp.viewmodel.PopularViewModel
import com.nbs.nucleosnucleo.presentation.viewbinding.NucleoFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : NucleoFragment<FragmentFavoriteBinding>() {

    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter(
            context = requireActivity(),
            items = mutableListOf()
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater, container, false)
    }

    override fun initIntent() {}

    override fun initUI() {
        binding.rvFavorite.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {
        getDummyData()
    }

    private fun getDummyData() {
        var data1 = FavoriteDetail(
            "tt0111161",
            "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_Ratio0.6716_AL_.jpg",
            "The Shawshank Redemption",
            "1994",
            "Adventure, Comedy"
        )

        var data2 = FavoriteDetail(
            "tt0068646",
            "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_Ratio0.7015_AL_.jpg",
            "The Godfather (1972)",
            "1972",
            "Adventure"
        )

        var listFavorite = listOf(data1, data2)

        favoriteAdapter.clear()
        favoriteAdapter.addOrUpdate(listFavorite)
    }
}