package com.example.ammymovie.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ammymovie.R
import com.example.ammymovie.databinding.FragmentDetailsBinding
import com.example.ammymovie.ui.main.model.Movie

class DetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "movie"
        fun newInstance(bundle: Bundle) = DetailsFragment().apply { arguments = bundle }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация данных
        val movie = arguments?.getParcelable<Movie>(BUNDLE_EXTRA)

        movie?.let {
            binding.titleRus.text = movie.name
            binding.titleOriginal.text = movie.nameOrigin
            binding.genre.text = movie.genre
            binding.duration.text = movie.duration
            binding.ratingDetail.text = movie.rating
            binding.revenue.text = movie.revenue
            binding.description.text = movie.description
            binding.dateRelease.text = movie.releaseDate.toString()
            binding.btnFavorite.setBackgroundResource(
                changeBackButton(movie.favorite)
            )
            binding.btnFavorite.setOnClickListener {
                val favorite = !movie.favorite
                binding.btnFavorite.setBackgroundResource(changeBackButton(favorite))
                movie.favorite = favorite
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