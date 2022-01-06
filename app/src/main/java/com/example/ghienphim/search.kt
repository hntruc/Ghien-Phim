package com.example.ghienphim

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ghienphim.model.FilmAdapterVH
import com.example.ghienphim.model.FilmsModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_search.*

class search : AppCompatActivity() {
    private val db:FirebaseFirestore = FirebaseFirestore.getInstance()
    private val data:FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var collectionReference:CollectionReference
    private lateinit var filmsList:MutableList<FilmsModel>
    private var filmAdapter:  FirestoreRecyclerAdapter<FilmsModel, FilmAdapterVH> ?=null
    private var TAG:  FirestoreRecyclerAdapter<FilmsModel, FilmAdapterVH> ?=null
    //    private lateinit var ref:DocumentReference
    private lateinit var name:String
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        filmsList = mutableListOf<FilmsModel>()
        val intent = getIntent()
        name = intent.getStringExtra("key").toString()
        collectionReference = db.collection("Films")

        setUpRecyclerview(this)

        //link to HomeScreen
        return_btn4.setOnClickListener {
            if(Cur.opt==1) {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, main_an::class.java)
                startActivity(intent)
                finish()
            }
        }

        home_search.setOnClickListener {
            if(Cur.opt==1) {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, main_an::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    fun setUpRecyclerview(context: Context){
//        val query: Query = collectionReference.whereEqualTo(name,ref)
        val query: Query = collectionReference.whereEqualTo("Name", name)
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<FilmsModel> = FirestoreRecyclerOptions.Builder<FilmsModel>()
                .setQuery(query, FilmsModel::class.java).build()

        filmsList = firestoreRecyclerOptions.snapshots.toMutableList()
        filmAdapter = object : FirestoreRecyclerAdapter<FilmsModel, FilmAdapterVH>(firestoreRecyclerOptions){
            override fun onBindViewHolder(holder: FilmAdapterVH, position: Int, model: FilmsModel) {
                if(filmsList.isEmpty()) {
                    filmsList.add(model)
                    val film = filmsList[position]
                }
                else {
                    val film = filmsList[position]
                }
                holder.filmName.text = model.Name
                holder.filmRate.text = model.Rating.toString()
                holder.filmName.setOnClickListener {
                    val intent = Intent(context, Description::class.java)
                    intent.putExtra("name",model.Name.toString())
                    intent.putExtra("Casts",model.Casts.toString())
                    intent.putExtra("Description",model.Description.toString())
                    intent.putExtra("ImageLink",model.ImageLink.toString())
                    intent.putExtra("Rating",model.Rating.toString())
                    startActivity(intent)
                    finish()
                }

            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapterVH {
                var view = LayoutInflater.from(parent.context).inflate(R.layout.row_films,parent,false)
                return FilmAdapterVH(view)
            }
            override fun onError(e: FirebaseFirestoreException) {
                e!!.message?.let { Log.e("error", it) }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = filmAdapter
    }

    override fun onStart()
    {
        super.onStart()
        filmAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        filmAdapter!!.stopListening()
    }
}

