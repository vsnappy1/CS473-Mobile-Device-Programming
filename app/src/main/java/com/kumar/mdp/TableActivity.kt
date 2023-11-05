package com.kumar.mdp

import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kumar.mdp.databinding.ActivityTableBinding


class TableActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTableBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addDummyRows()

        binding.buttonAdd.setOnClickListener {
            val androidVersion =  binding.editTextAndroidVersion.text.toString().trim()
            val androidCodeName = binding.editTextAndroidCodeName.text.toString().trim()

            if(androidVersion.isBlank() || androidCodeName.isBlank()){
                Toast.makeText(this, "Please enter both Android Version and Code Name.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            addTableRow(androidVersion, androidCodeName)
            restEditTextField()
            hideSoftKeyboard()
        }

    }

    private fun addDummyRows(){
        addTableRow("1.0", "No codename")
        addTableRow("1.1", "Petit Four")
        addTableRow("1.5", "Cupcake")
        addTableRow("1.6", "Donut")
        addTableRow("2.0", "Eclair")
    }
    private fun addTableRow(androidVersion: String, androidCodeName: String) {
        val tableRow = TableRow(this).apply {
            addView(TextView(this@TableActivity).apply { text = androidVersion }, 0)
            addView(TextView(this@TableActivity).apply { text = " $androidCodeName" }, 1)
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT)
            setBackgroundColor(getRowColor(binding.tableLayout.childCount))
        }

        binding.tableLayout.addView(tableRow)
    }

    private fun getRowColor(childCount: Int): Int {
        return Color.parseColor(if(childCount % 2 == 0) "#FFFFFF" else "#DDDDDD")
    }

    private fun hideSoftKeyboard(){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun restEditTextField(){
        binding.editTextAndroidVersion.text.clear()
        binding.editTextAndroidCodeName.text.clear()
        binding.editTextAndroidVersion.clearFocus()
        binding.editTextAndroidCodeName.clearFocus()
    }
}