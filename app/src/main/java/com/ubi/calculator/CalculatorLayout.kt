package com.ubi.calculator

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.ubi.calculator.databinding.CalculatorLayoutBinding


class CalculatorLayout : AppCompatActivity() {
  private lateinit var binding: CalculatorLayoutBinding

  override fun onCreate(state: Bundle?) {
    super.onCreate(state)

    binding = CalculatorLayoutBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.screen.background = VectorDrawableCompat.create(
      resources,
      R.drawable.inset_border,
      ContextThemeWrapper(this, Settings.screenTheme).theme
    )
  }
}