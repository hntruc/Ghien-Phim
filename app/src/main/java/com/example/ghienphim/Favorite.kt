package com.example.ghienphim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghienphim.model.IdTitleViewHolder
import com.example.ghienphim.model.SearchFilm
import com.example.ghienphim.sql.DatabaseHelper

import kotlinx.android.synthetic.main.activity_fav2.*
import kotlinx.android.synthetic.main.item_fav.*

class Favorite : AppCompatActivity() {
    private lateinit var categoriesList: ArrayList<String>
    private lateinit var database : DatabaseHelper
    private var adapter : AdapterFav ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav2)
        database = DatabaseHelper(this)
        categoriesList = database.getAllFilm() as ArrayList<String>
//        recyclerViewFav.layoutManager = LinearLayoutManager(this)
//        recyclerViewFav.adapter = AdapterFav(categoriesList)
//
//        recyclerViewFav.setHasFixedSize(true)
        adapter = AdapterFav(categoriesList)
        recyclerViewFav.layoutManager = LinearLayoutManager(this)
        recyclerViewFav.adapter = adapter
        // link to profile by return_btn2
        return_btn2.setOnClickListener {
            val intent = Intent(this, userprofile::class.java)
            startActivity(intent)
            finish()
        }
        //link to homescreen
        home_fav.setOnClickListener {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }


    }



    //
    class AdapterFav(val list: ArrayList<String>) : RecyclerView.Adapter<IdTitleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdTitleViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(com.example.ghienphim.R.layout.item_fav,parent,false)
            return IdTitleViewHolder(itemView)
        }


        override fun getItemCount(): Int {
            return list.size
        }


        override fun onBindViewHolder(holder: IdTitleViewHolder, position: Int) {
            val currentItem = list[position]
            holder.textViewTitle.text = currentItem.toString()
            //        holder.itemView.setOnClickListener {
            //            clickLambda(currentItem.id)
            //        }
        }

    } // IdTitleAdapter


}
