package com.example.ammymovie.ui.main.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.ammymovie.R
import com.example.ammymovie.databinding.FragmentDetailsBinding
import com.example.ammymovie.ui.main.model.Movie
import com.example.ammymovie.ui.main.model.getDefaultMovie
import com.example.ammymovie.ui.main.viewmodel.MovieDTO
import com.example.ammymovie.ui.main.viewmodel.MovieLoader
import com.example.ammymovie.ui.main.viewmodel.MovieLoaderListener

class DetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "movie"
        fun newInstance(bundle: Bundle) = DetailsFragment().apply { arguments = bundle }
    }
    private lateinit var movieBundle: Movie
    private val onLoadListener=
            object : MovieLoaderListener {

            override fun onLoaded(movieDTO: MovieDTO) {
                initView(movieDTO)
            }

            override fun onFailed(throwable: Throwable) {
                //Обработка ошибки
            }
        }

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
        val loader = MovieLoader(onLoadListener,movieBundle.id)
        loader.loadMovie()
    }

    private fun initView(movieDTO: MovieDTO) {
        with(binding) {
            mainView.visibility = View.VISIBLE
            titleRus.text = movieDTO.title
            titleOriginal.text = movieDTO.original_title
            //genre.text = movieDTO.genre
            duration.apply { text = movieDTO.duration }.hideIf { movieDTO.duration == "" }
            ratingDetail.text = movieDTO.popularity.toString()
            revenue.apply { text = movieDTO.revenue.toString() }.showIf { movieDTO.revenue != null }
            description.text = movieDTO.overview
            dateRelease.text = movieDTO.release_date
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

    private fun changeBackButton(favorite: Boolean) = when {
        favorite -> R.drawable.ic_baseline_favorite_16
        else -> R.drawable.ic_baseline_favorite_border_16
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}