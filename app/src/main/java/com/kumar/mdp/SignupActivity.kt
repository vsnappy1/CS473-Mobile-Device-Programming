package com.kumar.mdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kumar.mdp.data.User
import com.kumar.mdp.data.users
import com.kumar.mdp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreateAccount.setOnClickListener {
            val firstname = binding.editTextFirstname.text.toString().trim()
            val lastname = binding.editTextLastname.text.toString().trim()
            val email = binding.editTextEmailAddress.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (firstname.isBlank() ||
                lastname.isBlank() ||
                email.isBlank() ||
                password.isBlank()
            ) {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            users.add(User(firstname, lastname, email, password))
            finish()
        }
    }
}