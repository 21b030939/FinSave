package com.example.finsave.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finsave.R
import com.example.finsave.databinding.FragmentAnalysisPageBinding
import com.example.finsave.databinding.FragmentProfilePageBinding

class AnalysisPageFragment : Fragment() {

    private var _binding: FragmentAnalysisPageBinding? = null
    private val binding
        get() = _binding!!

    companion object{
        fun newInstance() = AnalysisPageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_analysis_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

}