package com.kumar.mdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kumar.mdp.databinding.ActivityShoppingBinding

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")
        email?.let {
            binding.textViewUsername.text = it
        }

        binding.linearLayoutElectronics.setOnClickListener {
            Toast.makeText(this, "Electronics category chosen.", Toast.LENGTH_SHORT).show()
        }
        binding.linearLayoutClothing.setOnClickListener {
            Toast.makeText(this, "Clothing category chosen.", Toast.LENGTH_SHORT).show()
        }
        binding.linearLayoutBeauty.setOnClickListener {
            Toast.makeText(this, "Beauty category chosen.", Toast.LENGTH_SHORT).show()
        }
        binding.linearLayoutFood.setOnClickListener {
            Toast.makeText(this, "Food category chosen.", Toast.LENGTH_SHORT).show()
        }
    }
}