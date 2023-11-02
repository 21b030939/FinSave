package com.example.finsave.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finsave.R
import com.example.finsave.databinding.FragmentProfilePageBinding

class ProfilePageFragment : Fragment() {

    private var _binding: FragmentProfilePageBinding? = null
    private val binding
        get() = _binding!!

    companion object{
        fun newInstance() = ProfilePageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

}