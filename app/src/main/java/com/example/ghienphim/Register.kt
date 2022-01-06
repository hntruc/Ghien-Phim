@file:Suppress("DEPRECATION")

package com.example.ghienphim


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import com.example.ghienphim.databinding.ActivityRegisterBinding
import com.example.ghienphim.model.CurrentUser
import com.example.ghienphim.model.Post
import com.example.ghienphim.model.User
import com.example.ghienphim.sql.DatabaseHelper
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class Register : AppCompatActivity() {

    private val activity = this@Register

    private lateinit var textInputEditEmail : EditText
    private lateinit var textInputEditUsername : EditText
    private lateinit var textInputEditPass : EditText
    private lateinit var textInputEditAge : EditText
    private lateinit var textInputEditConPass : EditText

    private lateinit var appCompatButtonRegister: AppCompatButton
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        database = FirebaseDatabase.getInstance().getReference("user")
        textInputEditAge = binding.editTuoi
        textInputEditConPass = binding.editXacnhanmatkhau
        textInputEditPass = binding.editMatkhau
        textInputEditEmail = binding.editEmail
        textInputEditUsername = binding.editTendangnhap

        initObjects()

        binding.returnBtn.setOnClickListener{
            val intent= Intent(this, Option::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnDangky.setOnClickListener {
            if (textInputEditEmail.text.isBlank() || textInputEditUsername.text.isBlank()
                || textInputEditPass.text.isBlank() || textInputEditConPass.text.isBlank()
                || textInputEditAge.text.isBlank()) {
                Toast.makeText(this, "Vui lòng nhập thông tin!!!", Toast.LENGTH_SHORT).show()
            } else {
//            Toast.makeText(this, textInputEditEmail.text, Toast.LENGTH_LONG).show()
                val opt = validInput(it)
                postDatatoSQLite(this,opt)
            }
        }
    }

    private fun initObjects(){
        databaseHelper = DatabaseHelper(activity)
    }
    private fun validInput(view: View):Int{
        val edit_A = textInputEditAge.text.toString().toInt()
        var opt :Int
        //check Password's length is in range 8 - 20 letter
        if(textInputEditPass.text.length !in 8..20)
            opt = 1
        //Check valid age
        else if(edit_A < 0 || edit_A > 100)
            opt = 2
        //check valid email
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(textInputEditEmail.text).matches())
            opt = 3
        //check if Pass and confirm Pass is different
        else if (!textInputEditPass.text.toString().equals(textInputEditConPass.text.toString()))
            opt = 4
        else
            opt = 0
        return opt
    }

    private fun writeNewPost(username:String, email: String, password:String,  age:String) {
        val key = database.child("username").push().key
        val post = Post( username, email, password,age)
        val postValues = post.toMap()
        database.child(username).setValue(postValues)
    }

    private fun postDatatoSQLite(context: Context,opt:Int){
        val check = action(context,opt)
        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        if (wifi!!.isConnected || mobile!!.isConnected) {
            if (check != 0) {
                var user = User(id = 1, name = textInputEditUsername.text.toString(), pass = textInputEditPass.text.toString(),
                    age = textInputEditAge.text.toString().toInt(), email = textInputEditEmail.text.toString())
                //databaseHelper.addUser(user)
                user.pass = user.pass.hashCode().toString();
                Cur.name = textInputEditUsername.toString()
                Cur.pass = textInputEditPass.toString()
                writeNewPost(user.name, user.email, user.pass, user.age.toString())
                emptyInputEditText()
                val intent = Intent(context, HomeScreen::class.java)
                startActivity(intent)
                finish()
            } else {
                val labelErr = AlertDialog.Builder(context)
                labelErr.setTitle(R.string.noti)
                labelErr.setMessage(R.string.exist_acc)
                labelErr.setIcon(R.drawable.alert_img)

                labelErr.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
                val alertDialog: AlertDialog = labelErr.create()
                alertDialog.show()
            }
        }
        else {
            val labelErr = AlertDialog.Builder(context)
            labelErr.setTitle(R.string.noti)
            labelErr.setMessage(R.string.disconnect)
            labelErr.setIcon(R.drawable.alert_img)

            labelErr.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
            val alertDialog: AlertDialog = labelErr.create()
            alertDialog.show()
        }
    }
    private fun emptyInputEditText() {
        textInputEditEmail!!.text = null
        textInputEditEmail!!.text = null
        textInputEditPass!!.text = null
        textInputEditAge!!.text = null
        textInputEditConPass!!.text = null
    }

    private fun action(context: Context, opt:Int) : Int{
        if (opt == 1) {
            val labelErr = AlertDialog.Builder(context)
            labelErr.setTitle(R.string.noti)
            labelErr.setMessage(R.string.wrongP)
            labelErr.setIcon(R.drawable.alert_img)

            labelErr.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
            val alertDialog: AlertDialog = labelErr.create()
            alertDialog.show()
            return 0
        } else if (opt == 2) {
            val labelErr = AlertDialog.Builder(context)
            labelErr.setTitle(R.string.noti)
            labelErr.setMessage(R.string.wrongA)
            labelErr.setIcon(R.drawable.alert_img)

            labelErr.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
            val alertDialog: AlertDialog = labelErr.create()
            alertDialog.show()
            return 0
        } else if (opt == 3) {
            val labelErr = AlertDialog.Builder(context)
            labelErr.setTitle(R.string.noti)
            labelErr.setMessage(R.string.invalidE)
            labelErr.setIcon(R.drawable.alert_img)

            labelErr.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
            val alertDialog: AlertDialog = labelErr.create()
            alertDialog.show()
            return 0
        } else if (opt == 4) {
            val labelErr = AlertDialog.Builder(context)
            labelErr.setTitle(R.string.noti)
            labelErr.setMessage(R.string.conDifPas)
            labelErr.setIcon(R.drawable.alert_img)

            labelErr.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
            val alertDialog: AlertDialog = labelErr.create()
            alertDialog.show()
            return 0
        } else {
            return 1
        }
    }
}