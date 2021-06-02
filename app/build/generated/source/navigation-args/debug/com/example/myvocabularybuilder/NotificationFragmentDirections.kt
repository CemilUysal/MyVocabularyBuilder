package com.example.myvocabularybuilder

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class NotificationFragmentDirections private constructor() {
  public companion object {
    public fun actionNotificationFragmentToCategoryListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_notificationFragment_to_categoryListFragment)
  }
}
