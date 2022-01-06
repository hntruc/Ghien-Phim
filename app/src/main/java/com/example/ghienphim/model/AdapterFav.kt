package com.example.ghienphim.model

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ghienphim.sql.DatabaseHelper
import kotlinx.android.synthetic.main.item_fav.view.*


//class AdapterFav(val list: ArrayList<String>) : RecyclerView.Adapter<IdTitleViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdTitleViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(com.example.ghienphim.R.layout.item_fav,parent,false)
//        return IdTitleViewHolder(itemView)
//    }
//
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//
//    override fun onBindViewHolder(holder: IdTitleViewHolder, position: Int) {
//        val currentItem = list[position]
//        holder.textViewTitle.text = currentItem.toString()
////        holder.itemView.setOnClickListener {
////            clickLambda(currentItem.id)
////        }
//    }
//
//} // IdTitleAdapter

class IdTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val textViewTitle: TextView = itemView.listFilmName

} // IdTitleViewHolder