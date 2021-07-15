package com.example.ammymovie.ui.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ammymovie.R
import com.example.ammymovie.ui.main.model.Movie
import com.example.ammymovie.ui.main.model.format

class UpcomingFragmentAdapter : RecyclerView.Adapter<UpcomingFragmentAdapter.ViewHolder>() {

    //Адаптер для второго списка
    internal var movieData: List<Movie> = emptyList()

    //второй способ реализации слуштеля, через функциональный интерфейс
    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_upcoming, parent, false) as View
        )
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
            //перепишем при помощи функции расширения
            itemView.apply {
                findViewById<TextView>(R.id.title_upcome).text = movie.name
                // меняем формат даты на DATE_TIME_FORMAT, и тип на string
                findViewById<TextView>(R.id.date_upcome).text =movie.releaseDate.format()
                findViewById<TextView>(R.id.genre_item).text = movie.genre
            // вызовем по цепочке
            }.also {
                    it.findViewById<CardView>(R.id.card_come).setOnClickListener {
                        itemClickListener?.onItemClick(movie)
                    }
                    it.findViewById<AppCompatImageView>(R.id.image_view_come).setOnClickListener {
                        itemClickListener?.onItemClick(movie)
                    }
            }

        }
    }
}

