package com.example.aarchive

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aarchive.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        fun isMail(a:String): Boolean {
            return a.endsWith("@cb.students.amrita.edu")
        }

        var i = Intent(this,HomePage::class.java)
        val radioGroup = findViewById<RadioGroup>(R.id.rg)


        binding.loginLoginBtn.setOnClickListener {
            val emaild = binding.loginEmailet.text.toString()
            val dataid = emaild.split("@").toTypedArray()[0]
            readData(dataid)
            when (binding.rg.checkedRadioButtonId) {

                R.id.rbteacher -> {
                    Log.d("_log", "teacher")
                    i = Intent(this, TeacherPageActivity::class.java)

                }
                R.id.rbstudent -> {
                    i = Intent(this, HomePage::class.java)
                }
                else -> {
                    Log.d("_log", "here")
                }
            }

            val email = binding.loginEmailet.text.toString()
            val pass = binding.loginPasset.text.toString()
            Log.d("_log","emal: "+email)
            Log.d("_log","pass: "+pass)

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(i)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Enter a valid mail or password",Toast.LENGTH_SHORT).show()
            }
        }
    // to login without going to login window... disabled for testing purposes
//    override fun onStart(){
//       super.onStart()
//       if(firebaseAuth.currentUser !=null){
//           val i = Intent(this,HomePage::class.java)
//           startActivity(i)
//       }
//    }

    }

    private fun readData(dataid: String) {
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(dataid).get().addOnSuccessListener {
                if(it.exists()){
                    val rollno = it.child("rollno").value

                    val sharedPreferences = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.apply {
                        putString("ROLL_KEY",rollno.toString())
                    }.apply()

                    Toast.makeText(this,rollno.toString(),Toast.LENGTH_SHORT).show()
                }
            }
    }
}