package com.example.ammymovie.ui.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ammymovie.R
import com.example.ammymovie.ui.main.model.Movie

class UpcomingFragmentAdapter : RecyclerView.Adapter<UpcomingFragmentAdapter.ViewHolder>() {

    private var movieData: List<Movie> = listOf()

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
                .inflate(R.layout.item_upcoming, parent, false) as View)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
       return movieData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            itemView.findViewById<TextView>(R.id.title_upcome).text = movie.name
            itemView.findViewById<TextView>(R.id.date_upcome).text = movie.releaseDate.toString()
            itemView.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    movie.name,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}