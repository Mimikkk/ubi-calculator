package com.ubi.calculator

import android.util.Log
import kotlin.math.pow

enum class Operator {
  Plus, Minus, Multiply, Divide, Power, Root;

  override fun toString(): String = when (this) {
    Plus -> "+"
    Minus -> "-"
    Multiply -> "*"
    Divide -> "/"
    Power -> "^"
    Root -> "√"
  }

  companion object {
    val OperatorStrings = values().map { it.toString() }
  }
}

class Calculator {
  private var levels = mutableListOf<String>()
    set(value) {
      field = value
      onChange(level, value)
    }
  private var level: String = ""
    set(value) {
      field = value
      onChange(value, levels)
    }

  fun add(value: String) {
    if (isNumeric(level) && !level.startsWith("0")) level += value
    else level = value
  }

  fun clear() {
    Log.i("clear", "clear")
    levels = mutableListOf()
    level = ""
  }

  fun period() {
    Log.i("period", "period")
    if ("." in level) return
    level += "."
  }

  fun undo() {
    Log.i("undo", "undo")
    if (level.isNotEmpty()) level = level.dropLast(1)
    else if (levels.isNotEmpty()) level = levels.removeAt(levels.size - 1)
  }

  fun enter() {
    Log.i("enter", "enter")
    if ((level.isEmpty() || level in Operator.OperatorStrings) && levels.isNotEmpty()) {
      levels.add(levels.last())
      onChange(level, levels)
    } else if (isNumeric(level)) {
      levels.add(level)
      onChange(level, levels)
      level = ""
    } else {
      level = ""
    }
  }

  fun swap() {
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

    onChange(level, levels)
  }

  fun drop() {
    Log.i("drop", "drop")
    if (level.isNotEmpty()) {
      level = ""
    } else if (levels.isNotEmpty()) {
      levels = levels.dropLast(1).toMutableList()
    }
  }

  fun plusminus() {
    Log.i("plusminus", "plusminus")
    if (isNumeric(level)) level = if (level.startsWith("-")) level.drop(1)
    else "-$level"
  }

  fun operate(operator: Operator) {
    Log.i("operate", "operate")
    if (levels.size < 2) {
      if (level.isEmpty() || !isNumeric(level) || levels.size < 1) {
        level = "Error!"
        Log.e("operate", "Error!")
        return
      }

      levels.add(level)
    }

    level = operator.toString()
    val second = levels.removeAt(levels.size - 1).toDouble()
    val first = levels.removeAt(levels.size - 1).toDouble()

    try {
      val value = when (operator) {
        Operator.Plus -> first + second
        Operator.Minus -> first - second
        Operator.Multiply -> first * second
        Operator.Divide -> first / second
        Operator.Root -> first.pow(1 / second)
        Operator.Power -> second.pow(first)
      }
      if (value.isInfinite()) throw ArithmeticException()
      levels.add(value.toString())
      onChange(level, levels)
    } catch (ignored: Exception) {
      level = "Error!"
    }
  }

  var onChange: (String, List<String>) -> Unit = { _, _ -> }

  private fun isNumeric(value: String): Boolean {
    return value.toDoubleOrNull() != null
  }
}