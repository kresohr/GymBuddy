package com.ikresimir.gymbuddy.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.TextView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Allow thread on activity so database can initialize.
        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        val viewModel = LoginViewModel()
        val txtLogin: TextView = findViewById(R.id.txtUsernameLogin)
        val txtPassword: TextView = findViewById(R.id.txtPasswordLogin)
        val lblRegister: TextView = findViewById(R.id.lblRegister)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            if (viewModel.loginUser(this,txtLogin.text.toString(),txtPassword.text.toString())){
                    val intent = Intent(this, ProfileActivity::class.java)
                    this.startActivity(intent)
            }
        }

        lblRegister.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            this.startActivity(intent)
        }
    }

}