package com.kumar.mdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kumar.mdp.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val foodItems = mutableListOf("Hamburger", "Pizza", "Mexican", "American", "Chinese")

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddFood.setOnClickListener {
            val foodItem = binding.editTextTextAddNewFood.text.toString().trim()
            if(foodItem.isNotEmpty()){
                foodItems.add(foodItem)
                binding.editTextTextAddNewFood.text.clear()
            }
        }

        binding.buttonDecideFood.setOnClickListener {
            val index = Random.nextInt(0,foodItems.size)
            binding.textViewFoodChoice.text = foodItems[index]
        }
    }
}