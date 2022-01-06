package com.example.ghienphim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ghienphim.history
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_history.*

class history : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        //link to Homescreen
        home_his.setOnClickListener {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }

        //link to Profile by return_btn2


        // delete all history film by trash
        // link to screen desciption

        his_1.setOnClickListener {
            val intent = Intent(this, Description::class.java)
            startActivity(intent)
            finish()
        }

        his_5.setOnClickListener {
            val intent = Intent(this, Description::class.java)
            startActivity(intent)
            finish()
        }


        his_name1.setOnClickListener {
            val intent = Intent(this, Description::class.java)
            startActivity(intent)
            finish()
        }

        his_name5.setOnClickListener {
            val intent = Intent(this, Description::class.java)
            startActivity(intent)
            finish()
        }

        return_btn3.setOnClickListener {
            val intent = Intent(this, userprofile::class.java)
            startActivity(intent)
            finish()
        }

    }
}