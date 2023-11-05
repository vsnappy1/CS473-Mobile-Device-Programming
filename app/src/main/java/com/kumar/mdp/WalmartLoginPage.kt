package com.kumar.mdp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kumar.mdp.data.User
import com.kumar.mdp.data.users
import com.kumar.mdp.databinding.ActivityWalmartLoginPageBinding

class WalmartLoginPage : AppCompatActivity() {


    private lateinit var binding: ActivityWalmartLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalmartLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignIn.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please enter both Email and Password.", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            val user = authenticate(email, password)
            user?.let {
                val intent = Intent(this, ShoppingActivity::class.java).apply {
                    putExtra("email", it.username)
                }
                startActivity(intent)
            }
                ?: Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
        }

        binding.buttonCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.textViewForgotPassword.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()

            if (email.isBlank()) {
                Toast.makeText(this, "Please enter you email.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = findByEmail(email)

            user?.let {
                val emailIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, it.username)
                    putExtra(Intent.EXTRA_SUBJECT, "Forgot Password Request")
                    putExtra(Intent.EXTRA_TEXT, "Your password is ${it.password}")
                }
                if (emailIntent.resolveActivity(packageManager) != null) {
                    startActivity(emailIntent);
                }
            } ?: Toast.makeText(this, "Email not found.", Toast.LENGTH_SHORT).show()

        }
    }

    private fun authenticate(email: String, password: String): User? {
        return users.find { user -> user.username == email && user.password == password }
    }

    private fun findByEmail(email: String): User? {
        return users.find { user -> user.username == email }
    }

}