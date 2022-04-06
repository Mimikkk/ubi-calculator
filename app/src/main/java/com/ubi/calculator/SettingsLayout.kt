package com.ubi.calculator

import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.ubi.calculator.databinding.SettingsLayoutBinding

class SettingsLayout : AppCompatActivity() {
  private lateinit var binding: SettingsLayoutBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = SettingsLayoutBinding.inflate(layoutInflater)
    setContentView(binding.root)

    handleThemeChange()
    binding.background.onItemSelectedListener = object : OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) = Unit

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
          0 -> Settings.screenTheme = R.style.GreenTheme
          1 -> Settings.screenTheme = R.style.RedTheme
          2 -> Settings.screenTheme = R.style.BlueTheme
        }
        handleThemeChange()
      }
    }
    binding.precision.onItemSelectedListener = object : OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) = Unit

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Settings.numberPrecision = position
      }
    }
  }

  fun handleThemeChange() {
    binding.screen.background = VectorDrawableCompat.create(
      resources,
      R.drawable.inset_border,
      ContextThemeWrapper(this, Settings.screenTheme).theme
    )
  }
}
