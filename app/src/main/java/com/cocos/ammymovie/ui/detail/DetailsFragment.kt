package com.cocos.ammymovie.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cocos.ammymovie.R
import com.cocos.ammymovie.databinding.FragmentDetailsBinding
import com.cocos.ammymovie.domain.model.MovieDTO
import com.cocos.ammymovie.ui.common.AppState
import com.cocos.ammymovie.utils.*

private const val BUNDLE_EXTRA = "movie"
class DetailsFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle) = DetailsFragment().apply { arguments = bundle }
    }

    private val movieBundle:MovieDTO? by lazy{
        arguments?.getParcelable(BUNDLE_EXTRA)
    }
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
        binding.mainView.visibility = View.GONE
        initViewModel()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        viewModel.detailsLiveData.observe(viewLifecycleOwner) { initView(it) }
        movieBundle?.let{
            viewModel.getMovieFromRemoteSource(it.id,"ru-RU")
        }

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
                        titleOriginal.text = movieDTO.originalTitle
                       // genre.text = movieDTO.genreIds.toString()
                        duration.apply { text = movieDTO.duration }
                            .hideIf { movieDTO.duration == "" }
                        ratingDetail.text = movieDTO.popularity.toString()
                        revenue.apply { text = movieDTO.revenue.toString() }
                            .showIf { movieDTO.revenue != null }
                        description.text = movieDTO.overview
                        dateRelease.text = movieDTO.releaseDate
                        imageViewCome.loadImageFromResource(movieDTO.posterPath)
                        movieBundle?.let{
                            movieDTO.favorite = it.favorite
                        }
                        btnFavorite.setBackgroundResource(
                            changeBackButton(movieDTO.favorite)
                        )
                        btnFavorite.setOnClickListener {
                            val favorite = !movieDTO.favorite
                            binding.btnFavorite.setBackgroundResource(changeBackButton(favorite))
                            movieDTO.favorite = favorite
                            viewModel.saveDetails(movieDTO)
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
                        { viewModel.getMovieFromRemoteSource(movieBundle?.id,"ru-Ru") })
                    mainView.hideKeyboard()
                }
                else -> mainView.showSnackBar("Не взлетел")
            }
        }
    }

    private fun changeBackButton(favorite: Boolean) =
        if(favorite) R.drawable.ic_baseline_favorite_16
        else R.drawable.ic_baseline_favorite_border_16

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}