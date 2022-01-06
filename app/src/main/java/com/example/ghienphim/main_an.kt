package com.example.ghienphim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_screen.*

class main_an : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_an)

        btn_theloai.setOnClickListener{
            val popup= PopupMenu(this,btn_theloai)
            popup.inflate(R.menu.list_popup_window_item)
            popup.setOnMenuItemClickListener{
                Toast.makeText(this, "Item : "+it.title, Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()
        }

        btn_quocgia.setOnClickListener{
            val popup= PopupMenu(this,btn_quocgia)
            popup.inflate(R.menu.show_quoc_gia)
            popup.setOnMenuItemClickListener{
                Toast.makeText(this, "Item : "+it.title, Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()
        }

        btn_nam.setOnClickListener{
            val popup= PopupMenu(this,btn_nam)
            popup.inflate(R.menu.show_nam)
            popup.setOnMenuItemClickListener{
                Toast.makeText(this, "Item : "+it.title, Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()
        }

        profile_btn.setOnClickListener{
            val intent= Intent(this, pro_an::class.java)
            startActivity(intent)
            finish()
        }

        search_btn.setOnClickListener{
            val intent= Intent(this, search::class.java)
            intent.putExtra("key",edit_timkiem.text.toString())
            startActivity(intent)
            finish()
        }

        home_film1.setOnClickListener{
            val intent= Intent(this, Description::class.java)
            startActivity(intent)
            finish()
        }

        home_film10.setOnClickListener{
            val intent= Intent(this, Description::class.java)
            startActivity(intent)
            finish()
        }
    }
}