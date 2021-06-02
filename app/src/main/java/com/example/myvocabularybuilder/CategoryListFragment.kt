package com.example.myvocabularybuilder

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import java.lang.Exception
import kotlinx.android.synthetic.main.fragment_category_list.*


class CategoryListFragment : Fragment() {
    val categoryPicker: DataPicker = DataPicker()
    val databaseProcess:DatabaseProcess = DatabaseProcess()

    private val CHANNEL_ID ="channel_id_exemple"
    private val description = "Test Notification"
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            context?.let{
                databaseProcess.createDataBase(it)
                for(i in categoryPicker.wordsAdder(it)){
                    databaseProcess.insertToCategory(it,i )
                }
            }
        }
        catch(e: Exception){
            println(e)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        verbs.setOnClickListener {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToCategoryFragment(verbs.text.toString())
            Navigation.findNavController(it).navigate(action)
        }
        adverbs.setOnClickListener {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToCategoryFragment(adverbs.text.toString())
            Navigation.findNavController(it).navigate(action)
        }
        adjectives.setOnClickListener {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToCategoryFragment(adjectives.text.toString())
            Navigation.findNavController(it).navigate(action)
        }
        phrasesAndIdioms.setOnClickListener {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToCategoryFragment(phrasesAndIdioms.text.toString())
            Navigation.findNavController(it).navigate(action)
        }
        addNewWords.setOnClickListener {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToAddWordFragment()
            Navigation.findNavController(it).navigate(action)
        }
        myWords.setOnClickListener {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToNotificationFragment()
            Navigation.findNavController(it).navigate(action)
        }
        quiz.setOnClickListener {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToQuizFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

}