package com.example.ghienphim.model
import com.google.firebase.database.IgnoreExtraProperties
data class User(val id: Int, val name:String, var pass: String, val email:String, val age: Int)


