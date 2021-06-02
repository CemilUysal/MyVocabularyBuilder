package com.example.myvocabularybuilder

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class WordFragmentArgs(
  public val category: String = "verbs",
  public val word: String
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("category", this.category)
    result.putString("word", this.word)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): WordFragmentArgs {
      bundle.setClassLoader(WordFragmentArgs::class.java.classLoader)
      val __category : String?
      if (bundle.containsKey("category")) {
        __category = bundle.getString("category")
        if (__category == null) {
          throw IllegalArgumentException("Argument \"category\" is marked as non-null but was passed a null value.")
        }
      } else {
        __category = "verbs"
      }
      val __word : String?
      if (bundle.containsKey("word")) {
        __word = bundle.getString("word")
        if (__word == null) {
          throw IllegalArgumentException("Argument \"word\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"word\" is missing and does not have an android:defaultValue")
      }
      return WordFragmentArgs(__category, __word)
    }
  }
}
