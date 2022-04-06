package com.ubi.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ubi.calculator.databinding.CalculatorLayoutBinding

class CalculatorLayout : AppCompatActivity() {
  private lateinit var binding: CalculatorLayoutBinding

  override fun onCreate(state: Bundle?) {
    super.onCreate(state)
    setContentView(R.layout.calculator_layout)
    binding = CalculatorLayoutBinding.inflate(layoutInflater)
  }
}