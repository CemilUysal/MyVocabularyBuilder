package com.example.myvocabularybuilder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_add_word.*
import java.io.ByteArrayOutputStream
import java.util.jar.Manifest


class AddWordFragment : Fragment() {
    var choosenImage: Uri? = null
    var choosenBitmap: Bitmap? = null
    val categoryChooser: CategoryChooser = CategoryChooser()
    val bitmapFixer: BitmapFixer = BitmapFixer()
    val wordObject: WordObject = WordObject()
    val databaseProcess: DatabaseProcess = DatabaseProcess()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            ArrayAdapter.createFromResource(
                    it,
                    R.array.word_category,
                    R.layout.support_simple_spinner_dropdown_item).also { adapter ->
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                toggleButtonCategory.adapter = adapter
            }
        }

        buttonSave.setOnClickListener {
            saveTheWord(it)

        }
        imageViewAdd.setOnClickListener{
            findPicture(it)
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
                            imageViewAdd.setImageBitmap(choosenBitmap)
                        }
                        else{
                            choosenBitmap = MediaStore.Images.Media.getBitmap(it.contentResolver, choosenImage!!)
                            choosenBitmap = bitmapFixer.fixedTheBitmap(choosenBitmap!!,300)
                            imageViewAdd.setImageBitmap(choosenBitmap)

                        }
                    }
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }

    }
    fun saveTheWord(view: View){
        if(choosenBitmap !=null ){
                wordObject.byteArray = bitmapFixer.BitmaptoByteArray(choosenBitmap!!)
                wordObject.word = editTextWord.text.toString()
                wordObject.antonyms = editTextAntonyms.text.toString()
                wordObject.meaning = editTextMeaning.text.toString()
                wordObject.sentence = editTextSentence.text.toString()
                wordObject.synonyms = editTextSynonyms.text.toString()
                wordObject.category = toggleButtonCategory.selectedItem.toString()
            context?.let{
                databaseProcess.insertToMyList(it, wordObject)
                databaseProcess.insertToCategory(it, wordObject)
            }
            val action = AddWordFragmentDirections.actionAddWordFragmentToCategoryListFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

}