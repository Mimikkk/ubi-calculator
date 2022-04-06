package com.ubi.calculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.ubi.calculator.databinding.CalculatorLayoutBinding
import kotlin.math.pow


class CalculatorLayout : AppCompatActivity() {
  companion object {
    private val calculator = Calculator()
  }

  private lateinit var binding: CalculatorLayoutBinding

  override fun onCreate(state: Bundle?) {
    super.onCreate(state)

    binding = CalculatorLayoutBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.settings.setOnClickListener {
      startActivity(Intent(this, SettingsLayout::class.java))
    }

    binding.num0.setOnClickListener { calculator.add("0") }
    binding.num1.setOnClickListener { calculator.add("1") }
    binding.num2.setOnClickListener { calculator.add("2") }
    binding.num3.setOnClickListener { calculator.add("3") }
    binding.num4.setOnClickListener { calculator.add("4") }
    binding.num5.setOnClickListener { calculator.add("5") }
    binding.num6.setOnClickListener { calculator.add("6") }
    binding.num7.setOnClickListener { calculator.add("7") }
    binding.num8.setOnClickListener { calculator.add("8") }
    binding.num9.setOnClickListener { calculator.add("9") }
    binding.period.setOnClickListener { calculator.period() }

    binding.plus.setOnClickListener { calculator.operate(Operator.Plus) }
    binding.minus.setOnClickListener { calculator.operate(Operator.Minus) }
    binding.division.setOnClickListener { calculator.operate(Operator.Divide) }
    binding.multiplication.setOnClickListener { calculator.operate(Operator.Multiply) }
    binding.sqrt.setOnClickListener { calculator.operate(Operator.Root) }
    binding.power.setOnClickListener { calculator.operate(Operator.Power) }

    binding.clear.setOnClickListener { calculator.clear() }
    binding.drop.setOnClickListener { calculator.drop() }
    binding.swap.setOnClickListener { calculator.swap() }
    binding.undo.setOnClickListener { calculator.undo() }
    binding.enter.setOnClickListener { calculator.enter() }
    binding.plusminus.setOnClickListener { calculator.plusminus() }

    calculator.onChange = { level, levels ->
      Log.i("Calculator", "onChange: $level, $levels")
    }
  }

  override fun onResume() {
    handleThemeChange()
    super.onResume()
  }

  private fun handleThemeChange() {
    binding.screen.background = VectorDrawableCompat.create(
      resources,
      R.drawable.inset_border,
      ContextThemeWrapper(this, Settings.screenTheme).theme
    )
  }

  private fun handlePrecisionChange() {}
}
