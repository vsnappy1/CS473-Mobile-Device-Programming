package com.kumar.mdp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kumar.mdp.databinding.QuestionActivityBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    private var score: Int = 0

    private lateinit var binding: QuestionActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestionActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {
            if (binding.radioButton2.isChecked) {
                score += 50
            }

            if (binding.checkBox.isChecked &&
                binding.checkBox2.isChecked &&
                binding.checkBox4.isChecked
            ) {
                score += 50
            }
            openDialog(score)
        }

        binding.buttonReset.setOnClickListener {
            reset()
        }
    }

    private fun openDialog(value: Int){
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)

        val message = if(value == 0) "You got 0% correct." else "Congratulations you got $value% correct. \n ${getCurrentDateFormatted()}"
        builder.setTitle("Result")
            .setMessage(message)

        val dialog: android.app.AlertDialog? = builder.create()
        dialog?.setOnDismissListener{
            reset()
        }
        dialog?.show()
    }

    private fun reset(){
        binding.apply {

            checkBox.isChecked = false
            checkBox2.isChecked = false
            checkBox3.isChecked = false
            checkBox4.isChecked = false

            radioButton.isChecked = false
            radioButton2.isChecked = false
            radioButton3.isChecked = false
            radioButton4.isChecked = false

            score = 0
        }
    }

    private fun getCurrentDateFormatted(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd") // Specify your desired date format

        return currentDate.format(formatter)
    }
}