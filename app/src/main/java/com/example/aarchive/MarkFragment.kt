package com.example.aarchive

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.core.widget.doOnTextChanged
import com.example.aarchive.databinding.FragmentMarkBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_mark.*
import kotlinx.android.synthetic.main.fragment_markt.*


class MarkFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var databasemark: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance().getReference("Users")

        val sharedPreferences = activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val r = sharedPreferences?.getString("ROLL_KEY","default")
        val rid =r.toString().split(".").toTypedArray()[2]

    var binding = FragmentMarkBinding.inflate(inflater, container, false)
        val subjects = resources.getStringArray(R.array.subjects)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,subjects)
        binding.autocompletetv.setAdapter(arrayAdapter)
        binding.autocompletetv.doOnTextChanged { text, start, before, count ->
            run {
                binding.headerTitle.setText(text)
            }
        }
        databasemark = FirebaseDatabase.getInstance().getReference("Marks")
        binding.getmarks.setOnClickListener {
            val subj = binding.autocompletetv.text.toString()
            databasemark.child(subj).child("Assignment 1").get().addOnSuccessListener{
                if (it.exists()){
                    val a1s = it.child(rid).child("value").value
                    binding.A1.text = a1s.toString()
                }
            }

            databasemark.child(subj).child("Assignment 2").get().addOnSuccessListener{
                if (it.exists()){
                    val a1s = it.child(rid).child("value").value
                    binding.A2.text = a1s.toString()
                }
            }

            databasemark.child(subj).child("Online quiz").get().addOnSuccessListener{
                if (it.exists()){
                    val a1s = it.child(rid).child("value").value
                    binding.oq.text = a1s.toString()
                }
            }

            databasemark.child(subj).child("Periodicals 1").get().addOnSuccessListener{
                if (it.exists()){
                    val a1s = it.child(rid).child("value").value
                    binding.p1.text = a1s.toString()
                }
            }

            databasemark.child(subj).child("Periodicals 2").get().addOnSuccessListener{
                if (it.exists()){
                    val a1s = it.child(rid).child("value").value
                    binding.p2.text = a1s.toString()
                }
            }

        }
        return binding.root
    }

}