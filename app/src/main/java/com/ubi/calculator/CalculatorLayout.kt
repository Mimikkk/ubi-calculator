package com.ubi.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.*
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.ubi.calculator.databinding.CalculatorLayoutBinding


class CalculatorLayout : AppCompatActivity() {

  private lateinit var binding: CalculatorLayoutBinding

  @SuppressLint("ClickableViewAccessibility")
  override fun onCreate(state: Bundle?) {
    super.onCreate(state)
    binding = CalculatorLayoutBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.settings.setOnClickListener {
      startActivity(Intent(this, SettingsLayout::class.java))
    }

    binding.num0.setOnClickListener { Calculator.add("0") }
    binding.num1.setOnClickListener { Calculator.add("1") }
    binding.num2.setOnClickListener { Calculator.add("2") }
    binding.num3.setOnClickListener { Calculator.add("3") }
    binding.num4.setOnClickListener { Calculator.add("4") }
    binding.num5.setOnClickListener { Calculator.add("5") }
    binding.num6.setOnClickListener { Calculator.add("6") }
    binding.num7.setOnClickListener { Calculator.add("7") }
    binding.num8.setOnClickListener { Calculator.add("8") }
    binding.num9.setOnClickListener { Calculator.add("9") }
    binding.period.setOnClickListener { Calculator.period() }

    binding.plus.setOnClickListener { Calculator.operate(Operator.Plus) }
    binding.minus.setOnClickListener { Calculator.operate(Operator.Minus) }
    binding.division.setOnClickListener { Calculator.operate(Operator.Divide) }
    binding.multiplication.setOnClickListener { Calculator.operate(Operator.Multiply) }
    binding.sqrt.setOnClickListener { Calculator.operate(Operator.Root) }
    binding.power.setOnClickListener { Calculator.operate(Operator.Power) }

    binding.clear.setOnClickListener { Calculator.clear() }
    binding.drop.setOnClickListener { Calculator.drop() }
    binding.swap.setOnClickListener { Calculator.swap() }
    binding.undo.setOnClickListener { Calculator.undo() }
    binding.enter.setOnClickListener { Calculator.enter() }
    binding.plusminus.setOnClickListener { Calculator.plusminus() }

    binding.root.setOnTouchListener(object : GestureListener(binding.root.context) {
        override fun onSwipeRight() {
          Calculator.undo()
        }
      })

    Calculator.onChange = this@CalculatorLayout::reRenderStackLevels
  }

  override fun onResume() {
    handleThemeChange()
    reRenderStackLevels(Calculator.level, Calculator.levels)
    super.onResume()
  }

  private fun handleThemeChange() {
    binding.screen.background = VectorDrawableCompat.create(
      resources,
      R.drawable.inset_border,
      ContextThemeWrapper(this, Settings.screenTheme).theme
    )
  }

  private fun reRenderStackLevels(level: String, levels: List<String>) {
    binding.screen.removeAllViews()
    levels.takeLast(3).forEachIndexed { index, level ->
      binding.screen.addView(createStackText(this,
        "Stack ${levels.size - index}:",
        level))
    }

    binding.screen.addView(createStackText(this,
      resources.getString(R.string.implication),
      level))
  }
}

fun createStackText(context: Context, prefix: String, text: String): LinearLayout =
  LinearLayout(context).apply {
    orientation = LinearLayout.HORIZONTAL
    layoutParams = LinearLayout.LayoutParams(
      WRAP_CONTENT,
      WRAP_CONTENT,
      1.0f
    )
    addView(TextView(context).apply {
      this.text = "$prefix "
      gravity = Gravity.START
      textSize = 24f
    })
    addView(Space(context))
    addView(TextView(context).apply {
      this.text = text
      gravity = Gravity.END
      textSize = 24f
    })
  }