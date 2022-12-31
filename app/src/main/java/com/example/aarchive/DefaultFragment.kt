package com.example.aarchive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import com.example.aarchive.databinding.FragmentDefaultBinding
import com.example.aarchive.databinding.FragmentMarkBinding

class DefaultFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentDefaultBinding.inflate(inflater, container, false)
        val subjects = resources.getStringArray(R.array.week)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,subjects)
//        autocompletetv.setAdapter(arrayAdapter)
        binding.autocompletetv.setAdapter(arrayAdapter)
        binding.autocompletetv.doOnTextChanged { text, start, before, count ->
            run {
                binding.headerTitle.setText(text)
            }
        }

//        return inflater.inflate(R.layout.fragment_mark, container, false)
        return binding.root
    }

    }