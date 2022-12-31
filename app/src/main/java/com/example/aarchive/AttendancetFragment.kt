package com.example.aarchive

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.aarchive.databinding.FragmentAttendancetBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AttendancetFragment : Fragment() {
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = FragmentAttendancetBinding.inflate(inflater, container, false)
        val subjects = resources.getStringArray(R.array.subjects)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,subjects)
        binding.subject.setAdapter(arrayAdapter)

        val exam = resources.getStringArray(R.array.slot)
        val arrayAdapter2 = ArrayAdapter(requireContext(),R.layout.dropdown_item,exam)
        binding.slot.setAdapter(arrayAdapter2)

        val rollno = resources.getStringArray(R.array.rollno)
        val arrayAdapter3 = ArrayAdapter(requireContext(),R.layout.dropdown_item,rollno)
        binding.rollno.setAdapter(arrayAdapter3)

        binding.submit.setOnClickListener {
            database = FirebaseDatabase.getInstance().getReference("Attendance")
            val date = binding.datatv.text.toString()//date
            val subject = binding.subject.text.toString()//subj
            val slot = binding.slot.text.toString()//slot
            val rid = binding.rollno.text.toString().split(".").toTypedArray()[2]//rollno id
            Log.d("_log","rid: "+rid)
            Log.d("_log","variable declaratio")
            if(binding.presence.isChecked){
                database.child(date.toString()).child(subject).child(rid).child(slot).setValue("1").addOnSuccessListener {
                    Log.d("_log","1 success")
                    database.child(rid).child(subject).child("attended").get().addOnSuccessListener {
                        Log.d("_log","2 success")
                        var s = it.value
                        if(s.toString().startsWith("null")){
                            s = 0
                        }
                        Log.d("_log","attended "+s.toString())
                        s = s.toString().toInt()+1
                        database.child(rid).child(subject).child("attended").setValue(s)
                        Log.d("_log","3 success")

                    }
                    database.child(rid).child(subject).child("total").get().addOnSuccessListener {
                        Log.d("_log","2 success")
                        var s = it.value
                        if(s.toString().startsWith("null")){
                            s = 0
                        }
                        Log.d("_log","total "+s.toString())
                        s = s.toString().toInt()+1
                        database.child(rid).child(subject).child("total").setValue(s)
                        Log.d("_log","3 success")
                    }
                }

        }
            else{
                database.child(date.toString()).child(subject).child(rid).child(slot).setValue("0").addOnSuccessListener {
                    Log.d("_log","1 success")
                    database.child(rid).child(subject).child("total").get().addOnSuccessListener {
                        Log.d("_log","2 success")
                        var s = it.value
                        if(s.toString().startsWith("null")){
                            s = 0
                        }
                        Log.d("_log","total"+s.toString())
                        s = s.toString().toInt()+1
                        database.child(rid).child(subject).child("total").setValue(s)
                        Log.d("_log","3 success")
                    }
                }
            }
        }






        return binding.root
    }

}

