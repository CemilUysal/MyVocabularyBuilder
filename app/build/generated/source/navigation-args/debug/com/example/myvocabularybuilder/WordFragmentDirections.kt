package com.example.myvocabularybuilder

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class WordFragmentDirections private constructor() {
  private data class ActionWordFragmentToCategoryFragment(
    public val categoryType: String = "verbs"
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_wordFragment_to_categoryFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("categoryType", this.categoryType)
      return result
    }
  }

  public companion object {
    public fun actionWordFragmentToCategoryFragment(categoryType: String = "verbs"): NavDirections =
        ActionWordFragmentToCategoryFragment(categoryType)
  }
}
