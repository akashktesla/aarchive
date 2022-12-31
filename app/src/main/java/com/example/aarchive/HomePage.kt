package com.example.aarchive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.aarchive.databinding.ActivityHomePageBinding
import com.example.aarchive.databinding.ActivityHomePageBinding.inflate
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.aarchive.databinding.ActivityHomePageBinding.bind as bind

class HomePage : AppCompatActivity() {
    val fragmentManager = supportFragmentManager
    lateinit var toggle:ActionBarDrawerToggle
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       database = FirebaseDatabase.getInstance().getReference()

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_frame_layout,DefaultFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    binding.homeNavView.setNavigationItemSelectedListener {
            when(it.itemId){


                R.id.nav_dash -> {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.home_frame_layout,DefaultFragment())
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()}

                R.id.nav_marks -> {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.home_frame_layout,MarkFragment())
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()}

                R.id.nav_attendance ->{
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.home_frame_layout,AttendanceFragment())
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()}

                R.id.nav_resource ->{
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.home_frame_layout,ResourceFragment())
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()}
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }



}