package com.example.ghienphim.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ghienphim.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_films.view.*

//class FilmAdapter(options: FirestoreRecyclerOptions<FilmsModel>) :
//    FirestoreRecyclerAdapter<FilmsModel, FilmAdapterVH>(options) {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapterVH {
//        return FilmAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.row_films,parent,false))
//    }
//
//    override fun onBindViewHolder(holder: FilmAdapterVH, position: Int, model: FilmsModel) {
////        val film = filmsList[position]
//        holder.filmName.text = model.Name
//        holder.filmRate.text = model.Rating.toString()
//
//        //holder.imgLink.
//        //Picasso.get().load(model.ImageLink).into(holder.imgLink)
//    }
//}
class FilmAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var filmName = itemView.tvFilmname
    var filmRate = itemView.tvRate
}