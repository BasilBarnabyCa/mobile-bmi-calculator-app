package ca.georgiancollege.bmicalculator

import android.content.Context
import android.util.Log
import ca.georgiancollege.bmicalculator.databinding.ActivityMainBinding

class BodyMassIndex (dataBinding: ActivityMainBinding, private val context: Context) {

    private var binding: ActivityMainBinding = dataBinding
    private var fullName: String
    private var age: String
    private var height: String
    private var weight: String
    private var unitType: String
    private var bmiValue: String
    private var categoryValue: String

    init {
        fullName =  ""
        age = ""
        height = ""
        weight = ""
        unitType = ""
        bmiValue = ""
        categoryValue = ""
        createButtons()
    }

    private fun createButtons() {
        binding.submitButton.setOnClickListener {
            calculateBmi()
        }

        binding.cancelButton.setOnClickListener {
            clearScreen()
        }

        binding.toggleButton.setOnClickListener {
            toggleUnitType()
        }
    }

    // toggle unittype
    private fun toggleUnitType () {
        binding.toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.heightUnitLabelText.text = "inches"
                binding.weightUnitLabelText.text = "pounds"
            } else {
                binding.heightUnitLabelText.text = "meters"
                binding.weightUnitLabelText.text = "kg"
            }
        }

        binding.ageEditTextView.setText("")
        binding.weightEditTextView.setText("")
        binding.heightEditTextView.setText("")
    }

    /** Calcualtes BMI */
    private fun calculateBmi()
    {
        val fullName = binding.fullNameEditTextView.text
        var age = binding.ageEditTextView.text
        val height = binding.heightEditTextView.text
        val weight = binding.weightEditTextView.text

        val bmi = (weight.toString().toDouble() * 703.0) / (height.toString().toDouble() * height.toString().toDouble() )

        if (bmi % 1 == 0.0) {
            binding.bmiTextView.text = bmi.toString().dropLast(2)
        } else {
            // Format to 8 decimal places and use regex to drop trailing zeros
            binding.bmiTextView.text = String.format("%.1f", bmi)
                .replace(Regex("\\.?0*$"), "")
        }

       if(bmi < 16) {
           binding.categoryTextView.text = "Severe Thinness"
       } else if(bmi in 16.0..17.0) {
           binding.categoryTextView.text = "Moderate Thinness"

       } else if(bmi in 17.0..18.5) {
           binding.categoryTextView.text = "Mild Thinness"

       } else if(bmi in 18.5..25.0) {
           binding.categoryTextView.text = "Normal"

       } else if(bmi in 25.0..30.0) {
           binding.categoryTextView.text = "Overweight"

       } else if(bmi in 30.0..35.0) {
           binding.categoryTextView.text = "Obese Class I"

       }else if(bmi in  35.0..40.0) {
           binding.categoryTextView.text = "Obese Class II"

       } else {
           binding.categoryTextView.text = "Obese Class III"
       }

        binding.progressBar.progress = bmi.toInt()
    }


    // Clear screen
    private fun clearScreen() {
        fullName = ""
        age = ""
        height = ""
        weight = ""
        bmiValue = ""
        categoryValue = ""

        binding.fullNameEditTextView.setText("")
        binding.ageEditTextView.setText("")
        binding.weightEditTextView.setText("")
        binding.heightEditTextView.setText("")

        binding.bmiTextView.text = ""
        binding.progressBar.progress = 0
        binding.categoryTextView.text = ""
    }
}