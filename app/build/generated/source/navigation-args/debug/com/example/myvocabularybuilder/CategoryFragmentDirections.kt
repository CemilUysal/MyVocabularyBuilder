package com.example.myvocabularybuilder

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class CategoryFragmentDirections private constructor() {
  private data class ActionCategoryFragmentToWordFragment(
    public val category: String = "verbs",
    public val word: String
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_categoryFragment_to_wordFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("category", this.category)
      result.putString("word", this.word)
      return result
    }
  }

  public companion object {
    public fun actionCategoryFragmentToCategoryListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_categoryFragment_to_categoryListFragment)

    public fun actionCategoryFragmentToWordFragment(category: String = "verbs", word: String):
        NavDirections = ActionCategoryFragmentToWordFragment(category, word)
  }
}
