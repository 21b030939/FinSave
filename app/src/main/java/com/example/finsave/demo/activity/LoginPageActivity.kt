package com.example.finsave.demo.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.finsave.R
import com.example.finsave.databinding.LoginPageActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.widget.RxTextView

@SuppressLint("Check Result")
class LoginPageActivity : AppCompatActivity() {

    private lateinit var binding: LoginPageActivityBinding
    private lateinit var auth: FirebaseAuth


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginPageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Authentication
        auth = FirebaseAuth.getInstance()

        //Username Validation
        val usernameStream =
            RxTextView.textChanges(binding.etEmail).skipInitialValue().map { username ->
                username.isEmpty()
            }
        usernameStream.subscribe {
            showTextMinimalAlert(isNotValid = it, kind = ErrorKind.EMAIL_OR_USERNAME)
        }

        //Password Validation
        val passwordStream =
            RxTextView.textChanges(binding.etPassword).skipInitialValue().map { password ->
                password.length < 8
            }
        passwordStream.subscribe {
            showTextMinimalAlert(isNotValid = it, kind = ErrorKind.PASSWORD)
        }

        //Button Enable True or False
        val invalidFieldsStream = io.reactivex.Observable.combineLatest(
            usernameStream,
            passwordStream,
            { usernameInvalid: Boolean, passwordInvalid: Boolean ->
                !usernameInvalid && !passwordInvalid
            })

        invalidFieldsStream.subscribe { isValid ->
            if (isValid) {
                binding.loginButton.isEnabled = true
                binding.loginButton.backgroundTintList =
                    ContextCompat.getColorStateList(this, R.color.dark_blue)
            } else {
                binding.loginButton.isEnabled = false
                binding.loginButton.backgroundTintList =
                    ContextCompat.getColorStateList(this, R.color.dark_grey)
            }
        }

        //Login
        binding.loginButton.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            loginUser(email, password)
        }


        binding.register.setOnClickListener {
            startActivity(Intent(this, RegPageActivity::class.java))
        }
    }

    private fun showTextMinimalAlert(isNotValid: Boolean, kind: ErrorKind) {
        if (kind == ErrorKind.EMAIL_OR_USERNAME) {
            binding.etEmail.error = if (isNotValid) "$kind cannot be empty" else null
        } else if (kind == ErrorKind.PASSWORD) {
            binding.etPassword.error = if (isNotValid) "$kind cannot be empty" else null
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { login ->
            if (login.isSuccessful) {
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, login.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

enum class ErrorKind {
    EMAIL_OR_USERNAME, PASSWORD
}