package com.example.ghienphim.model
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.util.HashMap
@IgnoreExtraProperties
public data class Post ( var name: String? = "", var email: String? ="", var password: String?="", var age: String? ="") {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
                //"id" to id,
                "name" to name,
                "email" to email,
                "password" to password,
                "age" to age
        )
    }





}