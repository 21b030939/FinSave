package com.example.finsave.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finsave.R
import com.example.finsave.databinding.FragmentProfilePageBinding
import com.example.finsave.databinding.FragmentRecordPageBinding

class RecordPageFragment : Fragment() {

    private var _binding: FragmentRecordPageBinding? = null
    private val binding
        get() = _binding!!

    companion object{
        fun newInstance() = RecordPageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

}