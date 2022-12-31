package com.example.aarchive

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.aarchive.databinding.FragmentMarktBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_mark.*
import kotlinx.android.synthetic.main.fragment_markt.view.*


class MarktFragment : Fragment() {
    //    private var biding: FragmentMarktBinding?=null
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var binding = FragmentMarktBinding.inflate(inflater, container, false)
        val subjects = resources.getStringArray(R.array.subjects)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,subjects)
        binding.autocompletetv.setAdapter(arrayAdapter)
        val exam = resources.getStringArray(R.array.exam)
        val arrayAdapter2 = ArrayAdapter(requireContext(),R.layout.dropdown_item,exam)
        binding.examtype.setAdapter(arrayAdapter2)
        val rollno = resources.getStringArray(R.array.rollno)
        val arrayAdapter3 = ArrayAdapter(requireContext(),R.layout.dropdown_item,rollno)
        binding.rollno.setAdapter(arrayAdapter3)
        //submit button storing in database
        database = FirebaseDatabase.getInstance().getReference("Marks")
        binding.submit.setOnClickListener {


            val sharedPreferences = activity?.getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
            val r = sharedPreferences?.getString("ROLL_KEY","default")
            Log.d("_log","rollno"+r.toString())

            Log.d("_log","submit button ")
            val subject = binding.autocompletetv.text.toString()
            val exam = binding.examtype.text.toString()
            val rollno = binding.rollno.text.toString().split(".").toTypedArray()[2]
            val m = binding.markss.text.toString()
            val mark = Mark(m)
            Log.d("_log","variable workinggg")

            database.child(subject).child(exam).child(rollno).setValue(mark).addOnSuccessListener {
                Log.d("_log","mark submitted sucessfully")
            }.addOnFailureListener {
                Log.d("_log","mark submitted sucessfully")
            }
        }
        return binding.root
    }

}