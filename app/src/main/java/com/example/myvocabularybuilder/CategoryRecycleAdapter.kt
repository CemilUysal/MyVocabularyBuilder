package com.example.myvocabularybuilder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_category.view.*
import kotlinx.android.synthetic.main.recycler_row.view.*

class CategoryRecycleAdapter(val wordList: ArrayList<String>, val wordIdList: ArrayList<Int>, val category: String) : RecyclerView.Adapter<CategoryRecycleAdapter.WordHolder>() {
    class WordHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row, parent, false)
        return WordHolder(view)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.itemView.recyclerCategory.text = wordList[position]
        holder.itemView.setOnClickListener{
            val action = CategoryFragmentDirections.actionCategoryFragmentToWordFragment(category, wordList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }
}