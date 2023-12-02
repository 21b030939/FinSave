package com.example.finsave.demo.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.finsave.R
import com.example.finsave.databinding.FragmentProfilePageBinding
import com.example.finsave.databinding.FragmentStatisticsPageBinding
import com.example.finsave.demo.activity.LoginPageActivity
import com.google.firebase.auth.FirebaseAuth

class ProfilePageFragment : Fragment() {

    private var _binding: FragmentProfilePageBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var auth: FirebaseAuth

    companion object {
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

        auth = FirebaseAuth.getInstance()
        binding.logoutButton.setOnClickListener {
            auth.signOut()
            Intent(this.requireContext(), LoginPageActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
                Toast.makeText(this.requireContext(), "Logout successful", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

}