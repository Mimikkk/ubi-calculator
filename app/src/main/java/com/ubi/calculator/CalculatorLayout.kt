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
  private lateinit var binding: CalculatorLayoutBinding
  private var levels = mutableListOf<String>()
    set(value) {
      field = value
      Log.d("level", level)
      Log.d("levels", value.toString())
    }
  private var level: String = ""
    set(value) {
      field = value
      Log.d("level", value)
      Log.d("levels", levels.toString())
    }

  override fun onCreate(state: Bundle?) {
    super.onCreate(state)

    binding = CalculatorLayoutBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.settings.setOnClickListener {
      startActivity(Intent(this, SettingsLayout::class.java))
    }

    binding.num0.setOnClickListener { add("0") }
    binding.num1.setOnClickListener { add("1") }
    binding.num2.setOnClickListener { add("2") }
    binding.num3.setOnClickListener { add("3") }
    binding.num4.setOnClickListener { add("4") }
    binding.num5.setOnClickListener { add("5") }
    binding.num6.setOnClickListener { add("6") }
    binding.num7.setOnClickListener { add("7") }
    binding.num8.setOnClickListener { add("8") }
    binding.num9.setOnClickListener { add("9") }
    binding.period.setOnClickListener { period() }

    binding.plus.setOnClickListener { operate("+") }
    binding.minus.setOnClickListener { operate("-") }
    binding.division.setOnClickListener { operate("/") }
    binding.multiplication.setOnClickListener { operate("*") }
    binding.sqrt.setOnClickListener { operate("s") }
    binding.power.setOnClickListener { operate("p") }

    binding.clear.setOnClickListener { clear() }
    binding.drop.setOnClickListener { drop() }
    binding.swap.setOnClickListener { swap() }
    binding.undo.setOnClickListener { undo() }
    binding.enter.setOnClickListener { enter() }
    binding.plusminus.setOnClickListener { plusminus() }
  }

  private fun add(value: String) {
    if (isNumeric(level) && !level.startsWith("0")) level += value
    else level = value
  }

  private fun clear() {
    Log.i("clear", "clear")
    levels = mutableListOf()
    level = ""
  }

  private fun period() {
    Log.i("period", "period")
    if ("." in level) return
    level += "."
  }

  private fun undo() {
    Log.i("undo", "undo")
    if (level.isNotEmpty()) level = level.dropLast(1)
    else if (levels.isNotEmpty()) level = levels.removeAt(levels.size - 1)
  }

  private fun enter() {
    Log.i("enter", "enter")

    if ((level.isEmpty() || level in "+-/*ps") && levels.isNotEmpty()) {
      levels.add(levels.last())
      levels = levels
    } else if (isNumeric(level)) {
      levels.add(level)
      levels = levels
      level = ""
    } else {
      level = ""
    }
  }

  private fun swap() {
    Log.i("swap", "swap")
    if (levels.isEmpty()) {
      if (level.isEmpty()) return
      levels.add(level)
      level = ""
      return
    }

    level = levels.removeAt(levels.size - 1).also {
      if (level.isNotEmpty()) levels.add(level)
    }

    levels = levels
  }

  private fun drop() {
    Log.i("drop", "drop")
    if (level.isNotEmpty()) {
      level = ""
    } else if (levels.isNotEmpty()) {
      levels = levels.dropLast(1).toMutableList()
    }
  }

  private fun plusminus() {
    Log.i("plusminus", "plusminus")
    if (isNumeric(level)) level = if (level.startsWith("-")) level.drop(1)
    else "-$level"
  }

  private fun operate(operator: String) {
    Log.i("operate", "operate")
    if (levels.size < 2) {
      if (level.isEmpty() || !isNumeric(level) || levels.size < 1) {
        level = "Error!"
        Log.e("operate", "Error!")
        return
      }

      levels.add(level)
    }

    level = operator
    val second = levels.removeAt(levels.size - 1).toDouble()
    val first = levels.removeAt(levels.size - 1).toDouble()
    try {
      val value = when (operator) {
        "+" -> first + second
        "-" -> first - second
        "*" -> first * second
        "/" -> first / second
        "s" -> first.pow(1 / second)
        "p" -> second.pow(first)
        else -> 0.0
      }
      if (value.isInfinite()) throw ArithmeticException()
      levels.add(value.toString())
      levels = levels
    } catch (e: Exception) {
      level = "Error!"
    }
  }

  override fun onResume() {
    handleThemeChange()
    handlePrecisionChange()
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

fun isNumeric(value: String): Boolean {
  return value.toDoubleOrNull() != null
}