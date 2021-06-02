package com.example.myvocabularybuilder

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import kotlinx.android.synthetic.main.fragment_add_word.*
import kotlinx.android.synthetic.main.fragment_word.*
import java.lang.Exception

class DatabaseProcess {
    val categoryChooser: CategoryChooser = CategoryChooser()
    val wordObject: WordObject = WordObject()
    var wordList: ArrayList<String> = ArrayList()
    var wordItems: ArrayList<WordObject> = ArrayList()
    fun createDataBase(context: Context){
        val database = context.openOrCreateDatabase("Vocabularies", Context.MODE_PRIVATE, null)
        database.execSQL("CREATE TABLE  mylist(id INTEGER PRIMARY KEY, category VARCHAR, word VARCHAR,meaning VARCHAR, sentence VARCHAR, synonyms VARCHAR, antonyms VARCHAR, picture BLOB)")
        database.execSQL("CREATE TABLE  verbs(id INTEGER PRIMARY KEY, word VARCHAR,meaning VARCHAR, sentence VARCHAR, synonyms VARCHAR, antonyms VARCHAR, picture BLOB)")
        database.execSQL("CREATE TABLE  adverbs(id INTEGER PRIMARY KEY, word VARCHAR,meaning VARCHAR, sentence VARCHAR, synonyms VARCHAR, antonyms VARCHAR, picture BLOB)")
        database.execSQL("CREATE TABLE  adjectives(id INTEGER PRIMARY KEY, word VARCHAR,meaning VARCHAR, sentence VARCHAR, synonyms VARCHAR, antonyms VARCHAR, picture BLOB)")
        database.execSQL("CREATE TABLE  phrasesAndIdioms(id INTEGER PRIMARY KEY, word VARCHAR,meaning VARCHAR, sentence VARCHAR, synonyms VARCHAR, antonyms VARCHAR, picture BLOB)")
        database.close()
    }
    fun insertToMyList(context: Context, word: WordObject){
        try {
            val database = context.openOrCreateDatabase("Vocabularies", Context.MODE_PRIVATE, null)
            val sqlString = "INSERT INTO mylist(word, meaning,sentence,synonyms,antonyms,picture,category) VALUES(?,?,?,?,?,?,?)"
            val statement =  database.compileStatement(sqlString)
            statement.bindString(1,word.word)
            statement.bindString(2,word.meaning)
            statement.bindString(3,word.sentence)
            statement.bindString(4, word.synonyms)
            statement.bindString(5, word.antonyms)
            statement.bindBlob(6, word.byteArray)
            statement.bindString(7, word.category)
            statement.execute()
            database.close()
        }
        catch (e: Exception){
            println("insert")
            println(e)
        }
    }
    fun insertToCategory(context: Context, word: WordObject){
        try {
            val database = context.openOrCreateDatabase("Vocabularies", Context.MODE_PRIVATE, null)
            val statement =  database.compileStatement(categoryChooser.chooseCategory(word.category))
            statement.bindString(1,word.word)
            statement.bindString(2,word.meaning)
            statement.bindString(3,word.sentence)
            statement.bindString(4, word.synonyms)
            statement.bindString(5, word.antonyms)
            statement.bindBlob(6, word.byteArray)
            statement.execute()
            database.close()
        }
        catch (e: Exception){
            println(e)
        }
    }
    fun sellectFromCategory(context: Context, sqlString: String, wordDefinition: String): WordObject{
        try {
            val database = context.openOrCreateDatabase("Vocabularies", Context.MODE_PRIVATE, null)

            val cursor = database.rawQuery(sqlString + " WHERE word = ?", arrayOf(wordDefinition))
            val idIndex = cursor.getColumnIndex("id")
            val wordIndex = cursor.getColumnIndex("word")
            val meaningIndex = cursor.getColumnIndex("meaning")
            val sentenceIndex = cursor.getColumnIndex("sentence")
            val synonymsIndex = cursor.getColumnIndex("synonyms")
            val antonymsIndex = cursor.getColumnIndex("antonyms")
            val pictureIndex = cursor.getColumnIndex("picture")
            val categoryIndex = cursor.getColumnIndex("category")
            while (cursor.moveToNext()) {
                wordObject.id = cursor.getInt(idIndex)
                wordObject.meaning  = cursor.getString(meaningIndex)
                wordObject.sentence = cursor.getString(sentenceIndex)
                wordObject.synonyms = cursor.getString(synonymsIndex)
                wordObject.antonyms = cursor.getString(antonymsIndex)
                wordObject.byteArray = cursor.getBlob(pictureIndex)
                wordObject.word = cursor.getString(wordIndex)
                wordObject.category = cursor.getString(categoryIndex)
                println("veriler okundu")
            }
            cursor.close()
            database.close()
        }
        catch (e: Exception) {
            println(e)
        }
        return wordObject
    }
    fun sellectAllWordsinOneTable(context: Context, sqlString: String): ArrayList<String>{
        try {
            val database = context.openOrCreateDatabase("Vocabularies", Context.MODE_PRIVATE, null)
            val cursor = database.rawQuery(sqlString,null)
            val wordIndex = cursor.getColumnIndex("word")
            wordList.clear()
            while (cursor.moveToNext()){
                wordList.add(cursor.getString(wordIndex))
            }

            cursor.close()
            database.close()
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return wordList
    }
    fun deleteWord(context: Context, id:Int){
        try {
            val database = context.openOrCreateDatabase("Vocabularies", Context.MODE_PRIVATE, null)
            database.execSQL("DELETE FROM mylist WHERE id = ?", arrayOf(id))
            database.close()
        }
        catch (e: Exception){
            println(e)
        }
    }
    fun updateWord(context: Context, word :WordObject){
        try {
            val database = context.openOrCreateDatabase("Vocabularies", Context.MODE_PRIVATE, null)
            database.execSQL(categoryChooser.updateCategory(word.category) + " ? WHERE id = ? ", arrayOf(word.byteArray, word.id))
            database.close()
        }
        catch (e: Exception){
            println("update")
            println(e)
        }
    }
    fun sellectAllItems( context: Context, sqlString: String): ArrayList<WordObject>{
        try {
            val database = context.openOrCreateDatabase("Vocabularies", Context.MODE_PRIVATE, null)
            wordItems.clear()
            val cursor = database.rawQuery(sqlString ,null)
            val idIndex = cursor.getColumnIndex("id")
            val wordIndex = cursor.getColumnIndex("word")
            val meaningIndex = cursor.getColumnIndex("meaning")
            val sentenceIndex = cursor.getColumnIndex("sentence")
            val synonymsIndex = cursor.getColumnIndex("synonyms")
            val antonymsIndex = cursor.getColumnIndex("antonyms")
            val pictureIndex = cursor.getColumnIndex("picture")
            while (cursor.moveToNext()) {
                var wordObje = WordObject()
                wordObje.id = cursor.getInt(idIndex)
                wordObje.meaning  = cursor.getString(meaningIndex)
                wordObje.sentence = cursor.getString(sentenceIndex)
                wordObje.synonyms = cursor.getString(synonymsIndex)
                wordObje.antonyms = cursor.getString(antonymsIndex)
                wordObje.byteArray = cursor.getBlob(pictureIndex)
                wordObje.word = cursor.getString(wordIndex)
                wordItems.add(wordObje)
            }
            cursor.close()
            database.close()
        }
        catch (e: Exception) {
            println(e)
        }
        return wordItems
    }
}