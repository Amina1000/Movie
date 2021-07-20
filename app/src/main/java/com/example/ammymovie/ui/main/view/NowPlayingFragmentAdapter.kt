package com.example.ammymovie.ui.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ammymovie.databinding.ItemNowPlayingBinding
import com.example.ammymovie.ui.main.model.Movie


class NowPlayingFragmentAdapter : RecyclerView.Adapter<NowPlayingFragmentAdapter.ViewHolder>() {

    // Адаптер для первого списка
    internal var movieData: List<Movie> = emptyList()
    private lateinit var binding:ItemNowPlayingBinding
    //первый способ реализации слушателя. Через функцию высшего порядка
    private var onSomeItemClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemNowPlayingBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding.root as View)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    // Сеттер слушателя нажатий
    fun setOnItemClickListener(onSomeItemClickListener: (Movie) -> Unit) {
        this.onSomeItemClickListener = onSomeItemClickListener
    }

    fun removeListener() {
        onSomeItemClickListener = null
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //перепишем при помощи функции расширения
        fun bind(movie: Movie) {
            with(binding){
                itemView.apply {
                    titlePlay.text = movie.name
                    rating.text = movie.rating
                    // меняем формат даты на DATE_TIME_FORMAT, и тип на string
                    datePlay.text = movie.releaseDate.format()
                    cardPlay.setOnClickListener {
                        onSomeItemClickListener?.invoke(movie)
                    }
                    imageViewPlay.createImageFromResource()
                    imageViewPlay.setOnClickListener {
                        onSomeItemClickListener?.invoke(movie)
                    }
                }
            }

        }
    }
}
