package com.example.aarchive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBtn.setOnClickListener {
            val i: Intent = Intent(this,LoginActivity::class.java)
            startActivity(i)
        }

        signupBtn.setOnClickListener {
            val i: Intent = Intent(this,SignupActivity::class.java)
            startActivity(i)
        }



    }
}