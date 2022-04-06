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
  private val PrecisionOptions = (0..10).toList()
  private val ThemeOptions = listOf("Red", "Green", "Blue")

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = SettingsLayoutBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.screen.background = VectorDrawableCompat.create(
      resources,
      R.drawable.inset_border,
      ContextThemeWrapper(this, Settings.screenTheme).theme
    )

    binding.background.onItemSelectedListener = object : OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) = Unit

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d("SettingsLayout", "onItemSelected: $position $id $view $parent")
      }
    }
  }


  private fun changeTheme(var1: AdapterView<*>?, var2: View?, var3: Int, var4: Long) {
    Log.e("Precision", "Precision changed")
  }

  private fun changePrecision(view: View) {
    Log.e("Precision", "Precision changed")
  }
}
