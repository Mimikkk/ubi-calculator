package com.ubi.calculator

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

open class GestureListener(context: Context?) : View.OnTouchListener {
  companion object {
    private const val SwipeThreshold = 100
    private const val SwipeVelocityThreshold = 100
  }

  private val gestureDetector: GestureDetector = GestureDetector(context, GestureListener())

  override fun onTouch(v: View, event: MotionEvent): Boolean {
    return gestureDetector.onTouchEvent(event)
  }

  private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
    override fun onDown(e: MotionEvent): Boolean {
      return true
    }

    override fun onFling(
      e1: MotionEvent,
      e2: MotionEvent,
      velocityX: Float,
      velocityY: Float,
    ): Boolean {
      var result = false
      try {
        val diffY = e2.y - e1.y
        val diffX = e2.x - e1.x
        if (abs(diffX) > abs(diffY)) {
          if (abs(diffX) > SwipeThreshold && abs(velocityX) > SwipeVelocityThreshold) {
            if (diffX > 0) {
              onSwipeRight()
            } else {
              onSwipeLeft()
            }
            result = true
          }
        } else if (abs(diffY) > SwipeThreshold && abs(velocityY) > SwipeVelocityThreshold) {
          if (diffY > 0) {
            onSwipeBottom()
          } else {
            onSwipeTop()
          }
          result = true
        }
      } catch (exception: Exception) {
        exception.printStackTrace()
      }

      return result
    }
  }

  open fun onSwipeRight() {}

  open fun onSwipeLeft() {}

  open fun onSwipeTop() {}

  open fun onSwipeBottom() {}
}