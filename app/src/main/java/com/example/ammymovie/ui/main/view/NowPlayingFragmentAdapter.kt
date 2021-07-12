package com.example.ammymovie.ui.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ammymovie.R
import com.example.ammymovie.ui.main.model.Movie

class NowPlayingFragmentAdapter: RecyclerView.Adapter<NowPlayingFragmentAdapter.ViewHolder>() {

    // Адаптер для первого списка
    private var movieData: List<Movie> = emptyList()
    private var itemClickListener: OnItemClickListener?=null

    fun setData(data: List<Movie>) {
        movieData = data
        notifyDataSetChanged()
    }

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

    // Интерфейс для обработки нажатий, как в ListView
    fun interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    // Сеттер слушателя нажатий
    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
    fun removeListener() {
        itemClickListener = null
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            itemView.findViewById<TextView>(R.id.title_play).text = movie.name
            itemView.findViewById<TextView>(R.id.rating).text = movie.rating
            itemView.findViewById<TextView>(R.id.date_play).text = movie.releaseDate.toString()
            itemView.findViewById<AppCompatImageView>(R.id.image_view_play)
            itemView.setOnClickListener {
                itemClickListener?.onItemClick(movie)
            }
        }
    }

}