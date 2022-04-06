package com.ubi.calculator

import java.io.Serializable

object Settings : Serializable {
  var numberPrecision: Int = 0
  var screenTheme: Int = R.style.DefaultTheme
}
