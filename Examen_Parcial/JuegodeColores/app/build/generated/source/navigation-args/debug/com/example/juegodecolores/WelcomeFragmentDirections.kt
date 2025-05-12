package com.example.juegodecolores

import androidx.`annotation`.CheckResult
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class WelcomeFragmentDirections private constructor() {
  public companion object {
    @CheckResult
    public fun actionWelcomeFragmentToGameFragment(): NavDirections = ActionOnlyNavDirections(R.id.action_welcomeFragment_to_gameFragment)
  }
}
