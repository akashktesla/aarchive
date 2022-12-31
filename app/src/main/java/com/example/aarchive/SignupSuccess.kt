package com.example.aarchive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup_success.*

class SignupSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_success)
        signupsucess_login.setOnClickListener {
            val i:Intent = Intent(this,LoginActivity::class.java)
            startActivity(i)
        }


    }
}