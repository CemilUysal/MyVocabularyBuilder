package com.example.myvocabularybuilder

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_quiz.*
import java.util.*
import kotlin.collections.ArrayList


class QuizFragment : Fragment() {
    val categoryChooser= CategoryChooser()
    val databaseProcess = DatabaseProcess()
    var wordList = ArrayList<WordObject>()
    var wrongAnswer = ArrayList<WordObject>()
    var rightAnswer = 0
    var question = 0
    var flag = 0
    val random = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            ArrayAdapter.createFromResource(
                    it,
                    R.array.word_category,
                    R.layout.support_simple_spinner_dropdown_item).also { adapter ->
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                textCategoryQuiz.adapter = adapter
            }
            textCategoryQuiz.onItemSelectedListener = object: OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val item = textCategoryQuiz.selectedItem.toString()
                    if(question==0){
                        quizStarter(item)
                    }
                    else{
                        Toast.makeText(context,"You must finnish the quiz",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        buttonAnswer1.setOnClickListener(){
            answerController(view,buttonAnswer1.text.toString())
        }
        buttonAnswer2.setOnClickListener(){
            answerController(view, buttonAnswer2.text.toString())

        }

    }

    fun quizStarter(item:String){
        wordList.clear()
        wrongAnswer.clear()

        val category = categoryChooser.sellectCategory(item)
        context?.let {

            wordList = databaseProcess.sellectAllItems(it, category)
            for (i in wordList){
                wrongAnswer.add(i)
            }
        }
        getQuestions()
    }
    fun getQuestions(){
        flag = random.nextInt(wordList.size)
        textWordQuiz.text = wordList.get(flag).word
        textAntonymsQuiz.text = wordList.get(flag).antonyms
        textSynonymsQuiz.text = wordList.get(flag).synonyms
        textSentenceQuiz.text = wordList.get(flag).sentence
        val byteDizisi = wordList.get(flag).byteArray
        val bitmap = BitmapFactory.decodeByteArray(byteDizisi, 0, byteDizisi.size)
        imageView2Quiz.setImageBitmap(bitmap)

        var wrong = random.nextInt(wrongAnswer.size)
        while(wordList.get(flag).meaning == wrongAnswer.get(wrong).meaning){
            wrong = random.nextInt(wrongAnswer.size)
        }
        if(random.nextBoolean()) {
            buttonAnswer1.text= wordList.get(flag).meaning
            buttonAnswer2.text= wrongAnswer.get(wrong).meaning
        }
        else{
            buttonAnswer2.text= wordList.get(flag).meaning
            buttonAnswer1.text= wrongAnswer.get(wrong).meaning
        }

    }
    fun answerController(view: View, string: String) {
        if (string.equals(wordList.get(flag).meaning, ignoreCase = true)) {
            rightAnswer++
            question++
            quizResult.text = rightAnswer.toString() + " / " + question
        }
        else {
            question++
            quizResult.text = rightAnswer.toString() + " / " + question
        }

        wordList.removeAt(flag)
        println(wrongAnswer.size)


        if (question < 10) {
            getQuestions()
        }
        else{
            context?.let {
                val builder = AlertDialog.Builder(it)
                builder.setTitle("Congratulations")
                builder.setMessage("You finish the game your score is $rightAnswer / $question. Do you want to do another quiz?")
                builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    rightAnswer = 0
                    question = 0
                    quizStarter(textCategoryQuiz.selectedItem.toString())
                })
                builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    view?.let {
                        rightAnswer = 0
                        question = 1
                        val action = QuizFragmentDirections.actionQuizFragmentToCategoryListFragment()
                        Navigation.findNavController(it).navigate(action)
                    }
                })
                builder.show()
            }
        }

    }
}