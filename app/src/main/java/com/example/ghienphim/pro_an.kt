package com.example.ghienphim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pro_an.*

class pro_an : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pro_an)

        return_btn2.setOnClickListener{
            val intent = Intent(this, main_an::class.java)
            startActivity(intent)
            finish()
        }

        btn_dangnhap.setOnClickListener{
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }

        btn_dangky.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }
    }
}