package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateBtn.setOnClickListener{ calculateTip() }
    }
    fun calculateTip() {
        val stringInTextField = binding.textInput.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.twenty_percent -> 0.20
            R.id.fifteen_percent -> 0.15
            else -> 0.10
        }
        var tip = cost * tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }
       val formattedTip =  NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        var totalAmount = tip + cost
        binding.total.text = getString(R.string.total_amount , totalAmount )
    }
}
