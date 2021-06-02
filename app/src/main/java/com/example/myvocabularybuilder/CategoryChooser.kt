package com.example.myvocabularybuilder

class CategoryChooser {
    var sqlString =""
    var selectSql=""
    fun chooseCategory(category:String): String{
        if (category.equals("verbs",ignoreCase = true)){
            sqlString = "INSERT INTO verbs (word, meaning,sentence,synonyms,antonyms,picture) VALUES(?,?,?,?,?,?)"
        }
        else if (category.equals("adverbs",ignoreCase = true)){
            sqlString = "INSERT INTO adverbs (word, meaning,sentence,synonyms,antonyms,picture) VALUES(?,?,?,?,?,?)"
        }
        else if (category.equals("adjectives",ignoreCase = true)){
            sqlString = "INSERT INTO adjectives (word, meaning,sentence,synonyms,antonyms,picture) VALUES(?,?,?,?,?,?)"
        }
        else{
            sqlString = "INSERT INTO phrasesAndIdioms (word, meaning,sentence,synonyms,antonyms,picture) VALUES(?,?,?,?,?,?)"
        }
        println(sqlString)
        return sqlString
    }
    fun sellectCategory(category: String):String{
        if (category.equals("verbs",ignoreCase = true)){
            selectSql = "SELECT * FROM verbs"
        }
        else if (category.equals("adverbs",ignoreCase = true)){
            selectSql = "SELECT * FROM adverbs"
        }
        else if (category.equals("adjectives",ignoreCase = true)){
            selectSql = "SELECT * FROM adjectives"
        }
        else{
            selectSql = "SELECT * FROM phrasesAndIdioms"
        }
        return selectSql
    }
    fun updateCategory(category: String):String{
        if (category.equals("verbs",ignoreCase = true)){
            selectSql = "UPDATE verbs SET picture = "
        }
        else if (category.equals("adverbs",ignoreCase = true)){
            selectSql = "UPDATE adverbs SET picture = "
        }
        else if (category.equals("adjectives",ignoreCase = true)){
            selectSql = "UPDATE adjectives SET picture = "
        }
        else{
            selectSql = "UPDATE phrasesAndIdioms SET picture = "
        }
        return selectSql
    }
}