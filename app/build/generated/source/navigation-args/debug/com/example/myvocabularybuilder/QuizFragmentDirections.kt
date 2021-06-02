package com.example.myvocabularybuilder

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class QuizFragmentDirections private constructor() {
  public companion object {
    public fun actionQuizFragmentToCategoryListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_quizFragment_to_categoryListFragment)
  }
}
