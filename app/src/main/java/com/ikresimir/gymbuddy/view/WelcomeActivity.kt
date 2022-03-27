package com.ikresimir.gymbuddy.view

import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.WelcomeViewModel

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        val viewModel = WelcomeViewModel()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent (this, LoginActivity::class.java)
            startActivity(intent)
        },3000)
        checkIfLogged(viewModel)
    }

    private fun checkIfLogged(viewModel: WelcomeViewModel){
        if(viewModel.checkIfLogged(this)){
            if (viewModel.checkIfProfileSet(this)){
                val intent = Intent(this,MenuActivity::class.java)
                this.startActivity(intent)
            }
            else{
                val intent = Intent(this,ProfileActivity::class.java)
                this.startActivity(intent)
            }
        }
        else{
            val intent = Intent(this,LoginActivity::class.java)
            this.startActivity(intent)
        }
    }
}