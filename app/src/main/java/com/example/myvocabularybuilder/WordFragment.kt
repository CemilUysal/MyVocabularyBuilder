package com.example.myvocabularybuilder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_add_word.*

import kotlinx.android.synthetic.main.fragment_word.*
import java.lang.Exception


class WordFragment : Fragment() {
    var categoryType= ""
    var wordDefinition = ""
    var choosenImage: Uri? = null
    var choosenBitmap: Bitmap? = null
    val bitmapFixer: BitmapFixer = BitmapFixer()
    val categoryChooser: CategoryChooser = CategoryChooser()
    val databaseProcess: DatabaseProcess = DatabaseProcess()
    var newword: WordObject = WordObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            categoryType = WordFragmentArgs.fromBundle(it).category
            wordDefinition = WordFragmentArgs.fromBundle(it).word
            textCategoryWord.text = categoryType
            textWordWord.text = wordDefinition
        }
        buttonSaveWord.setOnClickListener {
            addingMyList(it)
            val action = WordFragmentDirections.actionWordFragmentToCategoryFragment(categoryType)
            Navigation.findNavController(it).navigate(action)

        }
        imageView2Word.setOnClickListener(){
            findPicture(it)
        }
        sqlDataPicker()
    }
    fun sqlDataPicker() {
        context?.let {
            val sqlString = categoryChooser.sellectCategory(categoryType)
            val wordObject = databaseProcess.sellectFromCategory(it,sqlString,wordDefinition)
            textMeaning.text = wordObject.meaning
            textSentenceWord.text = wordObject.sentence
            textSynonymsWord.text = wordObject.synonyms
            textAntonymsWord.text = wordObject.antonyms
            val byteDizisi = wordObject.byteArray
            val bitmap = BitmapFactory.decodeByteArray(byteDizisi, 0, byteDizisi.size)
            imageView2Word.setImageBitmap(bitmap)
            textWordWord.text = wordObject.word
            println("veriler okundu")
            
            newword.byteArray = wordObject.byteArray
            newword.id = wordObject.id
        }
    }
    fun addingMyList(view: View){
        newword.category = textCategoryWord.text.toString()
        newword.word = textWordWord.text.toString()
        newword.sentence = textSentenceWord.text.toString()
        newword.meaning = textMeaning.text.toString()
        newword.antonyms = textAntonymsWord.text.toString()
        newword.synonyms = textSynonymsWord.text.toString()
        context?.let {
            databaseProcess.updateWord(it,newword)
            databaseProcess.insertToMyList(it,newword)
        }
    }
    fun findPicture(view: View){
        activity?.let{
            if(ContextCompat.checkSelfPermission(it.applicationContext,android.Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            }
            else{
                val mediaS = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(mediaS, 2)
            }
        }
    }
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val mediaS = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(mediaS, 2)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){
            choosenImage = data.data
            try {
                context?.let{
                    if(choosenImage != null){
                        if (Build.VERSION.SDK_INT >= 28){
                            val source = ImageDecoder.createSource(it.contentResolver, choosenImage!!)
                            choosenBitmap = ImageDecoder.decodeBitmap(source)
                            choosenBitmap = bitmapFixer.fixedTheBitmap(choosenBitmap!!,300)
                            newword.byteArray = bitmapFixer.BitmaptoByteArray(choosenBitmap!!)
                            imageView2Word.setImageBitmap(choosenBitmap)
                        }
                        else{
                            choosenBitmap = MediaStore.Images.Media.getBitmap(it.contentResolver, choosenImage!!)
                            choosenBitmap = bitmapFixer.fixedTheBitmap(choosenBitmap!!,300)
                            newword.byteArray = bitmapFixer.BitmaptoByteArray(choosenBitmap!!)
                            imageView2Word.setImageBitmap(choosenBitmap)
                        }
                    }
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }

    }


}