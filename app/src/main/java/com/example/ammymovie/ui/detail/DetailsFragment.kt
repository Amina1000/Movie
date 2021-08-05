package com.example.ammymovie.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ammymovie.R
import com.example.ammymovie.databinding.FragmentDetailsBinding
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.getDefaultMovie
import com.example.ammymovie.ui.common.AppState
import com.example.ammymovie.utils.*

private const val BUNDLE_EXTRA = "movie"
class DetailsFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle) = DetailsFragment().apply { arguments = bundle }
    }

    private lateinit var movieBundle: MovieDTO
    private lateinit var viewModel: DetailsViewModel

    private var _binding: FragmentDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: getDefaultMovie()
        binding.mainView.visibility = View.GONE
        initViewModel()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        viewModel.detailsLiveData.observe(viewLifecycleOwner) { initView(it) }
        viewModel.getMovieFromRemoteSource(movieBundle.id,"ru-RU")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initView(appState: AppState) {
        with(binding) {
            when (appState) {
                is AppState.SuccessDetails -> {
                    with(appState) {
                        loadingLayout?.hideIf { true }
                        mainView.visibility = View.VISIBLE
                        titleRus.text = movieDTO.title
                        titleOriginal.text = movieDTO.original_title
                        //genre.text = movieDTO.genre
                        duration.apply { text = movieDTO.duration }
                            .hideIf { movieDTO.duration == "" }
                        ratingDetail.text = movieDTO.popularity.toString()
                        revenue.apply { text = movieDTO.revenue.toString() }
                            .showIf { movieDTO.revenue != null }
                        description.text = movieDTO.overview
                        dateRelease.text = movieDTO.release_date
                        imageViewCome.loadImageFromResource(movieDTO.poster_path)
                        btnFavorite.setBackgroundResource(
                            changeBackButton(movieBundle.favorite)
                        )
                        btnFavorite.setOnClickListener {
                            val favorite = !movieBundle.favorite
                            binding.btnFavorite.setBackgroundResource(changeBackButton(favorite))
                            movieBundle.favorite = favorite
                        }
                    }
                }
                is AppState.Loading -> {
                    loadingLayout?.showIf { true }
                }
                is AppState.Error -> {
                    loadingLayout?.hideIf { true }
                    mainView.showSnackBar(getString(R.string.error),
                        getString(R.string.reload),
                        { viewModel.getMovieFromRemoteSource(movieBundle.id,"ru-Ru") })
                    mainView.hideKeyboard()
                }
                else -> mainView.showSnackBar("Не взлетел")
            }
        }
    }

    private fun changeBackButton(favorite: Boolean) = when {
        favorite -> R.drawable.ic_baseline_favorite_16
        else -> R.drawable.ic_baseline_favorite_border_16
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}