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

class NowPlayingFragmentAdapter : RecyclerView.Adapter<NowPlayingFragmentAdapter.ViewHolder>() {

    // Адаптер для первого списка
    internal var movieData: List<Movie> = emptyList()

    //первый способ реализации слушателя. Через функцию высшего порядка
    private var onSomeItemClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_now_playing, parent, false) as View
        )

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
            itemView.apply {
                findViewById<TextView>(R.id.title_play).text = movie.name
                findViewById<TextView>(R.id.rating).text = movie.rating
                // меняем формат даты на DATE_TIME_FORMAT, и тип на string
                findViewById<TextView>(R.id.date_play).text =movie.releaseDate.format()
                findViewById<CardView>(R.id.card_play).setOnClickListener {
                    onSomeItemClickListener?.invoke(movie)
                }
                findViewById<AppCompatImageView>(R.id.image_view_play).setOnClickListener {
                    onSomeItemClickListener?.invoke(movie)
                }
            }
        }
    }

}
