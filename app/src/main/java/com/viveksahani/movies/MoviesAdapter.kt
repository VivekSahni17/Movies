package com.viveksahani.movies

import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.viveksahani.movies.Movies as Movies

class MoviesAdapter(val listner:MoviesItemClicked):RecyclerView.Adapter<MoviesViewHolder>() {
    private val items:ArrayList<Movies> =ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.items_movies,parent,false)
        val viewHolder= MoviesViewHolder(view)
        view.setOnClickListener{
            listner.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text=currentItem.title
        holder.writer.text=currentItem.writer
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
       return items.size
    }
}

//  fun UpdateMovies(updatedmovies: ArrayList<Movies>){
//    items.clear()
//    items.addAll(updatedmovies)
//    notifyDataSetChanged()
//}

class MoviesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val titleView:TextView=itemView.findViewById(R.id.title)
    val image:ImageView=itemView.findViewById(R.id.image)
    val writer:TextView=itemView.findViewById(R.id.writer)
}

interface MoviesItemClicked{
    fun onItemClicked(item:Movies)
}