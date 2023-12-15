package com.kumar.mdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorld()
        }
    }
}

@Composable
fun HelloWorld(){
    Text(text = "Hello World")
}

@Preview
@Composable
fun PreviewHelloWorld(){
    HelloWorld()
}