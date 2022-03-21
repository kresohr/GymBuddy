package com.ikresimir.gymbuddy.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*


class ProfileActivity : AppCompatActivity() {

    private lateinit var txtAge : TextView
    private lateinit var txtFirstName : TextView
    private lateinit var txtUserHeight : TextView
    private lateinit var txtCurrentWeight : TextView
    private lateinit var radioBtnMale : RadioButton
    private lateinit var radioBtnFemale : RadioButton
    private lateinit var btnSaveBasicInfo: Button
    private lateinit var sex: String
    private var newUser = true

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        // Inicijalizacija svih gumbova i fieldova
        sex = "none"
        radioBtnMale = findViewById(R.id.radioBtnMale)
        radioBtnFemale = findViewById(R.id.radioBtnFemale)
        txtAge = findViewById(R.id.txtAge)
        txtFirstName = findViewById(R.id.txtFirstName)
        txtUserHeight = findViewById(R.id.txtUserHeight)
        txtCurrentWeight = findViewById(R.id.txtCurrentWeight)
        btnSaveBasicInfo = findViewById(R.id.btnSaveBasicInfo)
        val getUser = MainViewModel()
        checkIfOldUser(getUser)

        btnSaveBasicInfo.setOnClickListener {
            checkSex()
            if(checkIfFieldsEmpty()){
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                getUser.saveUserData(this,txtFirstName.text.toString(),
                    txtAge.text.toString().toInt(),txtUserHeight.text.toString().toDouble(),
                    txtCurrentWeight.text.toString().toDouble(),sex)
                val intent = Intent(this, GoalsActivity::class.java)
                this.startActivity(intent)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkIfOldUser(getUser: MainViewModel){
        getUser.getUserProfileData(this)
        newUser = getUser.newUser
        if (!newUser){
            txtFirstName.text = getUser.firstName
            txtAge.text = getUser.age.toString()
            txtCurrentWeight.text = getUser.userCurrentWeight.toString()
            txtUserHeight.text = getUser.userHeight.toString()
            when(getUser.sex){
                "m" -> radioBtnMale.isChecked=true
                "f" -> radioBtnFemale.isChecked=true
            }
        }
    }

    private fun checkSex(){
        if (radioBtnMale.isChecked)
            sex = "m"
        else if(radioBtnFemale.isChecked)
            sex = "z"
    }
    private fun checkIfFieldsEmpty(): Boolean{
        return (txtFirstName.text.isEmpty() || txtAge.text.isEmpty()
                || txtCurrentWeight.text.isEmpty() || txtUserHeight.text.isEmpty())
    }

}