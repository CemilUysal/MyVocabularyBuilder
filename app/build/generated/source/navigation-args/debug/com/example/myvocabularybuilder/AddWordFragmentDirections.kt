package com.example.myvocabularybuilder

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class AddWordFragmentDirections private constructor() {
  public companion object {
    public fun actionAddWordFragmentToCategoryListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_addWordFragment_to_categoryListFragment)
  }
}
