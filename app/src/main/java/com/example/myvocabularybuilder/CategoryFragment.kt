package com.example.myvocabularybuilder

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_category.*
import java.lang.Exception


class CategoryFragment : Fragment() {
    var catergoryType =""
    val categoryChooser: CategoryChooser = CategoryChooser()
    var wordDefinitionList = ArrayList<String>()
    var wordIdList = ArrayList<Int>()
    val databaseProcess: DatabaseProcess = DatabaseProcess()
    lateinit var listAdapter : CategoryRecycleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            catergoryType = CategoryFragmentArgs.fromBundle(it).categoryType
            kelime.text = catergoryType
        }

        kelime.setOnClickListener {
            val action = CategoryFragmentDirections.actionCategoryFragmentToCategoryListFragment()
            Navigation.findNavController(it).navigate(action)
        }
        listAdapter = CategoryRecycleAdapter(wordDefinitionList, wordIdList,catergoryType)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listAdapter

        sqlDataPicker()

    }
    fun sqlDataPicker(){
        val sqlString = categoryChooser.sellectCategory(catergoryType)
        wordDefinitionList.clear()
        context?.let {
            val wordList = databaseProcess.sellectAllWordsinOneTable(it, sqlString)
            for (i in wordList){

                wordDefinitionList.add(i)
            }
            listAdapter.notifyDataSetChanged()

        }
    }
}