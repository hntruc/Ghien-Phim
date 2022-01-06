package com.example.ghienphim

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ghienphim.databinding.ActivityChangePasswordBinding
import com.google.firebase.database.*


class change_password : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var textInputEditCurpass : EditText
    private lateinit var textInputEditNewpass : EditText
    private lateinit var textInputEditConfirm : EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)
        database = FirebaseDatabase.getInstance().getReference("user")

        textInputEditCurpass = binding.curEdit
        textInputEditNewpass = binding.newEdit
        textInputEditConfirm = binding.confirmEdit
        // link to Profile by btn_xacnhan
        binding.btnXacnhan.setOnClickListener {
            update_password(this)

        }

        binding.returnChangeP.setOnClickListener {
            val intent = Intent(this, userprofile::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun update_password(context: Context)
    {
        if (Cur.pass == textInputEditCurpass.text.toString()) {
            if (textInputEditConfirm.text.toString() == textInputEditNewpass.text.toString()) {
                if (textInputEditNewpass.text.length in 8..20) {
                    database.child(Cur.name.toString()).child("password")
                            .setValue(textInputEditConfirm.text.toString().hashCode())
                    val intent = Intent(context, userprofile::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val labelErr = AlertDialog.Builder(context)
                    labelErr.setTitle(R.string.noti)
                    labelErr.setMessage(R.string.wrongP)
                    labelErr.setIcon(R.drawable.alert_img)

                    labelErr.setNegativeButton("Huỷ", DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
                    val alertDialog: AlertDialog = labelErr.create()
                    alertDialog.show()
                }
            } else {
                val labelErr = AlertDialog.Builder(context)
                labelErr.setTitle(R.string.noti)
                labelErr.setMessage(R.string.new_confirm_Pass)
                labelErr.setIcon(R.drawable.alert_img)

                labelErr.setNegativeButton(
                    "Huỷ",
                    DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
                val alertDialog: AlertDialog = labelErr.create()
                alertDialog.show()
            }

        } else {
            val labelErr = AlertDialog.Builder(context)
            labelErr.setTitle(R.string.noti)
            labelErr.setMessage(R.string.new_current_Pass)
            labelErr.setIcon(R.drawable.alert_img)

            labelErr.setNegativeButton(
                "Huỷ",
                DialogInterface.OnClickListener() { dialog, id -> dialog.cancel() })
            val alertDialog: AlertDialog = labelErr.create()
            alertDialog.show()
        }
    }

}