package com.example.finsave.demo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finsave.databinding.AuthenticationPageActivityBinding
import com.google.firebase.auth.FirebaseAuth

class AuthenticationPageActivity : AppCompatActivity() {

    private lateinit var binding: AuthenticationPageActivityBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthenticationPageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginPageActivity::class.java))
        }

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, RegPageActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }


}