package com.example.juegodecolores

import androidx.`annotation`.CheckResult
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class ResultFragmentDirections private constructor() {
  public companion object {
    @CheckResult
    public fun actionResultFragmentToGameFragment(): NavDirections = ActionOnlyNavDirections(R.id.action_resultFragment_to_gameFragment)

    @CheckResult
    public fun actionResultFragmentToWelcomeFragment(): NavDirections = ActionOnlyNavDirections(R.id.action_resultFragment_to_welcomeFragment)
  }
}
