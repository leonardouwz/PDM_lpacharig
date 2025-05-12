package com.example.juegodecolores

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class ResultFragmentArgs(
  public val score: Int,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("score", this.score)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("score", this.score)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): ResultFragmentArgs {
      bundle.setClassLoader(ResultFragmentArgs::class.java.classLoader)
      val __score : Int
      if (bundle.containsKey("score")) {
        __score = bundle.getInt("score")
      } else {
        throw IllegalArgumentException("Required argument \"score\" is missing and does not have an android:defaultValue")
      }
      return ResultFragmentArgs(__score)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): ResultFragmentArgs {
      val __score : Int?
      if (savedStateHandle.contains("score")) {
        __score = savedStateHandle["score"]
        if (__score == null) {
          throw IllegalArgumentException("Argument \"score\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"score\" is missing and does not have an android:defaultValue")
      }
      return ResultFragmentArgs(__score)
    }
  }
}
