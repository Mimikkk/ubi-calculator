package com.ubi.calculator

import java.io.Serializable

object Settings : Serializable {
  var precision: Int = 4
  var screenTheme: Int = R.style.DefaultTheme
}
