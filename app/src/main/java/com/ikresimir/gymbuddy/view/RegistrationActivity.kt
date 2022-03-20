package com.ikresimir.gymbuddy.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.RegisterViewModel

class RegistrationActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val txtUsername: TextView = findViewById(R.id.txtUsernameRegistration)
        val txtPassword: TextView = findViewById(R.id.txtPasswordRegistration)
        val lblLogin: TextView = findViewById(R.id.lblLogin)
        val btnRegister: Button = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val viewModel = RegisterViewModel()
            viewModel.registerUser(this,txtUsername.text.toString(),txtPassword.text.toString())

            if (viewModel.successfullyRegistered){
                val intent = Intent(this, LoginActivity::class.java)
                this.startActivity(intent)
            }
        }
        lblLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
        }
    }
}