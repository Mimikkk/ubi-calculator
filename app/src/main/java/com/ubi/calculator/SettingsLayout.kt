package com.ubi.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ubi.calculator.databinding.SettingsLayoutBinding

class SettingsLayout : AppCompatActivity() {
  private lateinit var binding: SettingsLayoutBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.settings_layout)
    binding = SettingsLayoutBinding.inflate(layoutInflater)
  }
}
