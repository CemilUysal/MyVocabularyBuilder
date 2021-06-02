package com.example.myvocabularybuilder

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class CategoryFragmentArgs(
  public val categoryType: String = "verbs"
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("categoryType", this.categoryType)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): CategoryFragmentArgs {
      bundle.setClassLoader(CategoryFragmentArgs::class.java.classLoader)
      val __categoryType : String?
      if (bundle.containsKey("categoryType")) {
        __categoryType = bundle.getString("categoryType")
        if (__categoryType == null) {
          throw IllegalArgumentException("Argument \"categoryType\" is marked as non-null but was passed a null value.")
        }
      } else {
        __categoryType = "verbs"
      }
      return CategoryFragmentArgs(__categoryType)
    }
  }
}
