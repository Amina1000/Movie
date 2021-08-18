package com.example.ammymovie.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ammymovie.databinding.ItemNowPlayingBinding
import com.example.ammymovie.databinding.ItemUpcomingBinding
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.utils.loadImageFromResource

class CommonFragmentAdapter : RecyclerView.Adapter<CommonFragmentAdapter.ViewHolder>() {

    //Адаптер для второго списка
    internal var movieListData = listOf<MovieDTO>()
    private lateinit var binding: ItemNowPlayingBinding

    //второй способ реализации слуштеля, через функциональный интерфейс
    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemNowPlayingBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding.root as View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieListData[position])
    }

    override fun getItemCount(): Int {
        return movieListData.size
    }

    // Интерфейс для обработки нажатий, как в ListView
    fun interface OnItemClickListener {
        fun onItemClick(movie: MovieDTO)
    }

    // Сеттер слушателя нажатий
    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: MovieDTO) {
            with(binding) {
                //перепишем при помощи функции расширения
                itemView.apply {
                    titlePlay.text = movie.title
                    rating.text = movie.voteAverage.toString()
                    // меняем формат даты на DATE_TIME_FORMAT, и тип на string
                    datePlay.text = movie.releaseDate
                }.also {
                    cardPlay.setOnClickListener { itemClickListener?.onItemClick(movie) }
                    imageViewPlay.loadImageFromResource(movie.posterPath)
                    imageViewPlay.setOnClickListener { itemClickListener?.onItemClick(movie) }
                }
            }
        }
    }
}

