package com.example.ammymovie.ui.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ammymovie.databinding.ItemUpcomingBinding
import com.example.ammymovie.ui.main.model.Movie

class UpcomingFragmentAdapter : RecyclerView.Adapter<UpcomingFragmentAdapter.ViewHolder>() {

    //Адаптер для второго списка
    internal var movieData: List<Movie> = emptyList()
    private lateinit var binding: ItemUpcomingBinding
    //второй способ реализации слуштеля, через функциональный интерфейс
    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemUpcomingBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding.root as View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    // Интерфейс для обработки нажатий, как в ListView
    fun interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    // Сеттер слушателя нажатий
    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            with(binding){
                //перепишем при помощи функции расширения
                itemView.apply {
                    titleUpcome.text = movie.name
                    // меняем формат даты на DATE_TIME_FORMAT, и тип на string
                    dateUpcome.text =movie.releaseDate.format()
                    genreItem.text = movie.genre
                    // вызовем по цепочке
                }.also {
                    cardCome.setOnClickListener {itemClickListener?.onItemClick(movie)}
                    imageViewCome.setOnClickListener {itemClickListener?.onItemClick(movie)}
                }
            }
        }
    }
}

