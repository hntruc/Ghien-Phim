@file:Suppress("DEPRECATION")

package com.example.ghienphim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.ghienphim.sql.DatabaseHelper
import com.example.ghienphim.model.User
import com.example.ghienphim.model.Post

import com.example.ghienphim.databinding.ActivityLoginBinding
import androidx.databinding.DataBindingUtil
import com.example.ghienphim.model.CurrentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.*
import java.lang.IllegalArgumentException




class LogIn : AppCompatActivity() {

    private val activity = this@LogIn

    private lateinit var textInputEditUsername : EditText
    private lateinit var textInputEditPass : EditText

    private lateinit var appCompatButtonRegister: AppCompatButton
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityLoginBinding

    private var cur_user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        database = FirebaseDatabase.getInstance().getReference("user")
        textInputEditUsername = binding.editTendangnhap
        textInputEditPass = binding.editMatkhauDn

        initObjects()
        binding.returnBtnDn.setOnClickListener {
            val intent = Intent(this, Option::class.java)
            startActivity(intent)
            finish()
        }

        // alertbox login failed
        binding.btnDn.setOnClickListener {
            if (textInputEditUsername.text.isBlank() || textInputEditPass.text.isBlank()) {
                Toast.makeText(this, "Vui lòng nhập thông tin!!!", Toast.LENGTH_SHORT).show()
            } else {
                if (!validInput(it)) {
                    val errPopUp = AlertDialog.Builder(this)
                    errPopUp.setTitle(R.string.noti)
                    errPopUp.setMessage(R.string.wrongP)
                    errPopUp.setIcon(R.drawable.alert_img)
                    errPopUp.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
                    val alertDialog: AlertDialog = errPopUp.create()
                    alertDialog.show()
                } else {
                    verifyFromData(this)
                }
            }
        }
    }
    private fun verifyFromData(context: Context) {
        //if(databaseHelper!!.checkUser(username = textInputEditUsername.text.toString(), password = textInputEditPass.text.toString())){
        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        if (wifi!!.isConnected || mobile!!.isConnected) {
            if (textInputEditUsername.text.toString() != null && textInputEditPass.text.toString() != null) {
                val cur_user = textInputEditUsername.text.toString()
                var cur_pass = textInputEditPass.text.toString()

                Cur.name = textInputEditUsername.text.toString()
                Cur.pass = textInputEditPass.text.toString()

                cur_pass = cur_pass.hashCode().toString()


                //database.child(cur_user)
                database.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        var name = ""
                        for (post in snapshot.children) {
                            if (post.key == cur_user) {
                                name = post.getValue().toString()
                                break
                            }
                        }
                        var result = ""
                        var i = 10
                        while (name[i] != ',') {
                            result += name[i]
                            i++;
                        }
                        if (result == cur_pass) {
                            val intent = Intent(context, HomeScreen::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else
                        {
                            val labelErr = AlertDialog.Builder(context)
                            labelErr.setTitle(R.string.noti)
                            labelErr.setMessage(R.string.wrong_acc)
                            labelErr.setIcon(R.drawable.alert_img)

                            labelErr.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
                            val alertDialog: AlertDialog = labelErr.create()
                            alertDialog.show()
                        }

                        if (name == "")
                        {
                            val labelErr = AlertDialog.Builder(context)
                            labelErr.setTitle(R.string.noti)
                            labelErr.setMessage(R.string.unavai_acc)
                            labelErr.setIcon(R.drawable.alert_img)

                            labelErr.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id ->
                                dialog.cancel()
                                val intent = Intent(context, Option::class.java)
                                startActivity(intent)
                                finish()
                            })
                            val alertDialog: AlertDialog = labelErr.create()
                            alertDialog.show()
                        }

                    }
                })
            }
        }
        else
        {
            val labelErr = AlertDialog.Builder(context)
            labelErr.setTitle(R.string.noti)
            labelErr.setMessage(R.string.disconnect)
            labelErr.setIcon(R.drawable.alert_img)

            labelErr.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
            val alertDialog: AlertDialog = labelErr.create()
            alertDialog.show()
        }
    }

    private fun validInput(view: View):Boolean{
        //check Password's length is in range 8 - 20 letter
        if(textInputEditPass.text.length !in 8..20)
            return false
        return true
    }
    private fun initObjects(){
        databaseHelper = DatabaseHelper(activity)
    }
}