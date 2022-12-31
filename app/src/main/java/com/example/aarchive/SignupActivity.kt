package com.example.aarchive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.aarchive.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.signupSignupBtn.setOnClickListener {
            val a = binding.signupNameet.text.toString()
            val b = binding.signupRollnoet.text.toString()
            val email = binding.signupEmailet.text.toString()
            val dataid = email.split("@").toTypedArray()[0]

            database = FirebaseDatabase.getInstance().getReference("Users")
            val user = User(a,b)
            database.child(dataid).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"registration to database is successfull",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"registration to database is failed",Toast.LENGTH_SHORT).show()
            }
            val pass  = binding.signupPasset.text.toString()
            val confirmpass = binding.signupConfirmet.text.toString()

            Log.d("_log","pass"+pass)
            Log.d("_log","confirm"+confirmpass)

            if (email.isNotEmpty()&&pass.isNotEmpty()&&confirmpass.isNotEmpty()){
                if (pass == confirmpass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if (it.isSuccessful){


                            val i = Intent(this,LoginActivity::class.java)
                            startActivity(i)
                        }
                        else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                       Toast.makeText(this,"password is not matching",Toast.LENGTH_SHORT).show()
                    }
            }
                else{
                   Toast.makeText(this,"Don't leave the fields empty",Toast.LENGTH_SHORT).show()
                   }
                }
            }
        }