package com.kumar.mdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kumar.mdp.adapter.ElectronicItemAdapter
import com.kumar.mdp.data.cart
import com.kumar.mdp.data.products
import com.kumar.mdp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter =
            ElectronicItemAdapter(products,
                onItemClick = {},
                onAddItemClick = {
                    cart.add(it)
                })

        binding.buttonViewCart.setOnClickListener {
            Toast.makeText(this, cart.toString(), Toast.LENGTH_LONG).show()
        }
    }
}