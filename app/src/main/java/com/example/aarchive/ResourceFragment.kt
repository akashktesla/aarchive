package com.example.aarchive

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.aarchive.databinding.FragmentMarkBinding
import com.example.aarchive.databinding.FragmentResourceBinding
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class ResourceFragment : Fragment() {
    lateinit var uri : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var binding = FragmentResourceBinding .inflate(inflater, container, false)
        binding.pdfBtn.setOnClickListener {
            view: View? -> val intent = Intent()
            intent.setType ("pdf/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            uri = intent!!.data!!
            binding.uriTxt.text = uri.toString()


        }
        return binding.root
    }
}