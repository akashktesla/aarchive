package com.example.aarchive

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aarchive.databinding.FragmentAttendanceBinding
import com.example.aarchive.databinding.FragmentAttendancetBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AttendanceFragment : Fragment() {

    private lateinit var binding: FragmentAttendanceBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttendanceBinding.inflate(inflater,container,false)
        binding.refresh.setOnClickListener {
            var temp: String = ""
            val sharedPreferences =
                activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            val r = sharedPreferences?.getString("ROLL_KEY", "default")
            val rid = r.toString().split(".").toTypedArray()[2]
            database = FirebaseDatabase.getInstance().getReference("Attendance")

            database.child(rid).child("App dev").child("attended").get().addOnSuccessListener {
                temp = it.value.toString()
            }
            database.child(rid).child("App dev").child("total").get().addOnSuccessListener {
                val percent =(temp.toFloat()/it.value.toString().toFloat())*100
                Log.d("_log","percent"+percent.toString())
                temp = temp + "/" + it.value.toString()
                Log.d("_log",temp)
                binding.attendance1.text = temp
                binding.attendancep1.text = percent.toInt().toString()+"%"
            }


            database.child(rid).child("Big data").child("attended").get().addOnSuccessListener {
                temp = it.value.toString()
            }
            database.child(rid).child("Big data").child("total").get().addOnSuccessListener {
                val percent =(temp.toFloat()/it.value.toString().toFloat())*100
                Log.d("_log","percent"+percent.toString())
                temp = temp + "/" + it.value.toString()
                Log.d("_log",temp)
                binding.attendance2.text = temp
                binding.attendancep2.text = percent.toInt().toString()+"%"
            }


            database.child(rid).child("Operating systems").child("attended").get().addOnSuccessListener {
                temp = it.value.toString()
            }
            database.child(rid).child("Operating systems").child("total").get().addOnSuccessListener {
                val percent =(temp.toFloat()/it.value.toString().toFloat())*100
                Log.d("_log","percent"+percent.toString())
                temp = temp + "/" + it.value.toString()
                Log.d("_log",temp)
                binding.attendance3.text = temp
                binding.attendancep3.text = percent.toInt().toString()+"%"
            }


            database.child(rid).child("Psychology").child("attended").get().addOnSuccessListener {
                temp = it.value.toString()
            }
            database.child(rid).child("Psychology").child("total").get().addOnSuccessListener {
                val percent =(temp.toFloat()/it.value.toString().toFloat())*100
                Log.d("_log","percent"+percent.toString())
                temp = temp + "/" + it.value.toString()
                Log.d("_log",temp)
                binding.attendance4.text = temp
                binding.attendancep4.text = percent.toInt().toString()+"%"
            }


            database.child(rid).child("Business communication").child("attended").get().addOnSuccessListener {
                temp = it.value.toString()
            }
            database.child(rid).child("Business communication").child("total").get().addOnSuccessListener {
                val percent =(temp.toFloat()/it.value.toString().toFloat())*100
                Log.d("_log","percent"+percent.toString())
                temp = temp + "/" + it.value.toString()
                Log.d("_log",temp)
                binding.attendance5.text = temp
                binding.attendancep5.text = percent.toInt().toString()+"%"
            }



        }
            return binding.root
    }
}

