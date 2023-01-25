package com.annisaarss.movieapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.annisaarss.movieapp.R
import com.annisaarss.movieapp.databinding.FragmentHomeBinding
import com.annisaarss.movieapp.domain.movie.model.MostPopularDetail
import com.annisaarss.movieapp.presentation.detail.DetailActivity
import com.annisaarss.movieapp.presentation.home.adapter.ViewPagerAdapter
import com.annisaarss.movieapp.viewmodel.HomeViewModel
import com.nbs.nucleo.data.Result
import com.nbs.nucleo.utils.showToast
import com.nbs.nucleosnucleo.presentation.viewbinding.NucleoFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : NucleoFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }

    override fun initIntent() {}

    override fun initUI() {
        binding.layoutPopular.setKeyText(getString(R.string.popular_movies))
        binding.layoutCoomingSoon.setKeyText(getString(R.string.cooming_soon))
    }

    override fun initAction() {}

    override fun initProcess() {
        homeViewModel.getPosterPopular()
        homeViewModel.getBanner()
        homeViewModel.getPosterCoomingSoon()
    }

    override fun initObservers() {
        homeViewModel.listPosterPopular.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    hideLoading()
                    binding.layoutPopular.setMovie(it.data)
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })

        homeViewModel.listBanner.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    hideLoading()
                    setBanner(it.data)
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })

        homeViewModel.listPosterCoomingSoon.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    hideLoading()
                    binding.layoutCoomingSoon.setMovie(it.data)
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })
    }

    private fun setBanner(banner: List<String>) {
        viewPagerAdapter = ViewPagerAdapter(requireActivity(), banner)
        binding.vpSlider.adapter = viewPagerAdapter
        binding.dotsIndicator.attachTo(binding.vpSlider)
    }
}