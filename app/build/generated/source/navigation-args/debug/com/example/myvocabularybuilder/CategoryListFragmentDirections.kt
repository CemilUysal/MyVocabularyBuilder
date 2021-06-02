package com.example.myvocabularybuilder

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class CategoryListFragmentDirections private constructor() {
  private data class ActionCategoryListFragmentToCategoryFragment(
    public val categoryType: String = "verbs"
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_categoryListFragment_to_categoryFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("categoryType", this.categoryType)
      return result
    }
  }

  public companion object {
    public fun actionCategoryListFragmentToQuizFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_categoryListFragment_to_quizFragment)

    public fun actionCategoryListFragmentToAddWordFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_categoryListFragment_to_addWordFragment)

    public fun actionCategoryListFragmentToNotificationFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_categoryListFragment_to_notificationFragment)

    public fun actionCategoryListFragmentToCategoryFragment(categoryType: String = "verbs"):
        NavDirections = ActionCategoryListFragmentToCategoryFragment(categoryType)
  }
}
