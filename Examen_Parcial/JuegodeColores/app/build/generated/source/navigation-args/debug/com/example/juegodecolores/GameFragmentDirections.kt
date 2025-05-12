package com.example.juegodecolores

import android.os.Bundle
import androidx.`annotation`.CheckResult
import androidx.navigation.NavDirections
import kotlin.Int

public class GameFragmentDirections private constructor() {
  private data class ActionGameFragmentToResultFragment(
    public val score: Int,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_gameFragment_to_resultFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("score", this.score)
        return result
      }
  }

  public companion object {
    @CheckResult
    public fun actionGameFragmentToResultFragment(score: Int): NavDirections = ActionGameFragmentToResultFragment(score)
  }
}
