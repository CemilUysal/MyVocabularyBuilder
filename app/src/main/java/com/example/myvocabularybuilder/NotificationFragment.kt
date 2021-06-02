package com.example.myvocabularybuilder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_notification.*
import java.util.*


class NotificationFragment : Fragment() {
    val databaseProcess: DatabaseProcess = DatabaseProcess()
    var myWord = WordObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonMemorized.setOnClickListener {
            context?.let {
                Toast.makeText(it,"${myWord.word} is deleted from your list.",Toast.LENGTH_SHORT).show()
                databaseProcess.deleteWord(it, myWord.id)
                sqlDataPicker()
            }

        }
        buttonRemind.setOnClickListener {
            val action = NotificationFragmentDirections.actionNotificationFragmentToCategoryListFragment()
            Navigation.findNavController(it).navigate(action)
        }
        sqlDataPicker()
    }

    fun sqlDataPicker(){
            context?.let {

                val myWordlist = databaseProcess.sellectAllWordsinOneTable(it, "SELECT * FROM mylist")
                if(myWordlist.size != 0){
                    val random= Random()
                    val randomWord = random.nextInt(myWordlist.size)
                    myWord = databaseProcess.sellectFromCategory(it, "SELECT * FROM mylist",myWordlist[randomWord])

                    textSentenceNoti.text = myWord.sentence
                    textSynonymsNoti.text = myWord.synonyms
                    textAntonymsNoti.text = myWord.antonyms
                    textCategoryNoti.setText(myWord.category)
                    val byteDizisi = myWord.byteArray
                    val bitmap = BitmapFactory.decodeByteArray(byteDizisi, 0, byteDizisi.size)
                    imageView2Noti.setImageBitmap(bitmap)
                    textWordNoti.text = myWord.word
                    textCategoryNoti.text = myWord.category
                    println("veriler okundu")
                }
            }
    }


}