package com.example.ammymovie.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ammymovie.databinding.ItemNowPlayingBinding
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.utils.loadImageFromResource

class NowPlayingFragmentAdapter : RecyclerView.Adapter<NowPlayingFragmentAdapter.ViewHolder>() {

    // Адаптер для первого списка
    internal var movieList = ArrayList<MovieDTO>()
    private lateinit var binding: ItemNowPlayingBinding

    //первый способ реализации слушателя. Через функцию высшего порядка
    private var onSomeItemClickListener: ((MovieDTO) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemNowPlayingBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding.root as View)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    // Сеттер слушателя нажатий
    fun setOnItemClickListener(onSomeItemClickListener: (MovieDTO) -> Unit) {
        this.onSomeItemClickListener = onSomeItemClickListener
    }

    fun removeListener() {
        onSomeItemClickListener = null
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //перепишем при помощи функции расширения
        fun bind(movie: MovieDTO) {
            with(binding) {
                itemView.apply {
                    titlePlay.text = movie.title
                    rating.text = movie.voteAverage.toString()
                    // меняем формат даты на DATE_TIME_FORMAT, и тип на string
                    datePlay.text = movie.releaseDate
                    cardPlay.setOnClickListener {
                        onSomeItemClickListener?.invoke(movie)
                    }
                    imageViewPlay.loadImageFromResource(movie.posterPath)
                    imageViewPlay.setOnClickListener {
                        onSomeItemClickListener?.invoke(movie)
                    }
                }
            }

        }
    }
}
