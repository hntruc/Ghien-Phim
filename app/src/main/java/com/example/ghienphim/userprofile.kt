package com.example.ghienphim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.ghienphim.databinding.ActivityLoginBinding
import com.example.ghienphim.databinding.ActivityUserprofileBinding
import kotlinx.android.synthetic.main.activity_userprofile.*

class userprofile : AppCompatActivity()
{
    private lateinit var binding: ActivityUserprofileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_userprofile)
        profile_name.text = Cur.name.toString()

        binding.btnPyt.setOnClickListener{
            val intent = Intent(this, Favorite::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLichsu.setOnClickListener{
            val intent = Intent(this, history::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnDmk.setOnClickListener{
            val intent = Intent(this, change_password::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnDangxuat.setOnClickListener{
            val intent = Intent(this, Option::class.java)
            startActivity(intent)
            finish()
        }

        binding.returnBtn2.setOnClickListener {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}