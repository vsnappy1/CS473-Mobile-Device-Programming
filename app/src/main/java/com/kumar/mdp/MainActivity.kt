package com.kumar.mdp

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.kumar.mdp.navigation.NavigationGardening


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navHostController = rememberNavController()
                NavigationGardening(navHostController, onScanPlantClick = { openCamera() })
            }
        }
    }

    private fun openCamera() {
        // Create an implicit Intent to capture an image
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Check if there's a camera app to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Start the camera activity
            startActivity(takePictureIntent)
        }
    }
}