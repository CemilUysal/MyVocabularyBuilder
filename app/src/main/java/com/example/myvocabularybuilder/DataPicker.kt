package com.example.myvocabularybuilder

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.fragment_add_word.*

class DataPicker {
    var choosenBitmap: Bitmap? = null
    val bitmapFixer: BitmapFixer= BitmapFixer()
    val wordsBitmap = arrayOf(R.drawable.play, R.drawable.watch, R.drawable.read,R.drawable.go, R.drawable.write,
            R.drawable.study,R.drawable.work,R.drawable.listen,R.drawable.call,R.drawable.come,R.drawable.together,R.drawable.again,R.drawable.slowly,R.drawable.quickly,R.drawable.extremly,
            R.drawable.daily,R.drawable.early,R.drawable.too,R.drawable.probably,R.drawable.below,R.drawable.bad,R.drawable.good,R.drawable.serious,R.drawable.fast,R.drawable.slow,
            R.drawable.little,R.drawable.big,R.drawable.old,R.drawable.young,R.drawable.small,R.drawable.achip, R.drawable.apice,R.drawable.anarm,R.drawable.backto,R.drawable.barking,
            R.drawable.beetwen,R.drawable.burst,R.drawable.curiosity,R.drawable.cutto,R.drawable.adime)


    val wordsByteArray: ArrayList<ByteArray> = ArrayList()
    var words: ArrayList<WordObject> = ArrayList()


    fun wordsAdder(context: Context): ArrayList<WordObject>{
        for (i in wordsBitmap){
            choosenBitmap = BitmapFactory.decodeResource(context.resources,i)
            choosenBitmap = bitmapFixer.fixedTheBitmap(choosenBitmap!!,300)
            wordsByteArray.add(bitmapFixer.BitmaptoByteArray(choosenBitmap!!))
        }
        words.add(WordObject("verbs","play","oynamak","I play football","bowl","underbid",wordsByteArray[0]))
        words.add(WordObject("verbs","watch","izlemek","I watch netflix series","look","agitate",wordsByteArray[1]))
        words.add(WordObject("verbs","read","okumak","I read book","interpret","dock",wordsByteArray[2]))
        words.add(WordObject("verbs","go","gitmek","I go to the gym","drag","rise",wordsByteArray[3]))
        words.add(WordObject("verbs","write","yazmak","I write some stories","outline","sheathe",wordsByteArray[4]))
        words.add(WordObject("verbs","study","ders calismak","I study Mobile Devices","major","divest",wordsByteArray[5]))
        words.add(WordObject("verbs","work","calismak","I work at home","serve","outfield",wordsByteArray[6]))
        words.add(WordObject("verbs","listen","dinlemek","I listen Eminem","hang","exclude",wordsByteArray[7]))
        words.add(WordObject("verbs","call","aramak","I will call you","telecommunicate","disorderliness",wordsByteArray[8]))
        words.add(WordObject("verbs","come","gelmek","I came my home","arrive","leave",wordsByteArray[9]))
        words.add(WordObject("adverbs","together","birlikte","You and me together","unitedly","meladjusted",wordsByteArray[10]))
        words.add(WordObject("adverbs","again","yeniden","Try again later","once more","none",wordsByteArray[11]))
        words.add(WordObject("adverbs","slowly","yavasca","I drive slowly","slow","quickly",wordsByteArray[12]))
        words.add(WordObject("adverbs","quickly","cabucak","I came quickl my home","rapidly","slowly",wordsByteArray[13]))
        words.add(WordObject("adverbs","extremely","zor","I climb extremly nice","highly","birth",wordsByteArray[14]))
        words.add(WordObject("adverbs","daily","gunluk","I write daily story","day by day","informality",wordsByteArray[15]))
        words.add(WordObject("adverbs","early","erkenden","I woke up early","early on","recede",wordsByteArray[16]))
        words.add(WordObject("adverbs","too","çok","I love you too","overly","none",wordsByteArray[17]))
        words.add(WordObject("adverbs","probably","muhtemelen","I came probably","likely","impossible",wordsByteArray[18]))
        words.add(WordObject("adverbs","below","altında","There was a store below the my building","beneath","above",wordsByteArray[19]))
        words.add(WordObject("adjectives","bad","kotu","I saw bad dream","tough","good",wordsByteArray[20]))
        words.add(WordObject("adjectives","good","iyi","I saw good dream","groovy","bad",wordsByteArray[21]))
        words.add(WordObject("adjectives","serious","ciddi","I have serious problem","earnest","frivality",wordsByteArray[22]))
        words.add(WordObject("adjectives","fast","hızlı","I run fast","quick","slow",wordsByteArray[23]))
        words.add(WordObject("adjectives","slow","yavas","I walk slow ","lazy","fast",wordsByteArray[24]))
        words.add(WordObject("adjectives","little","kucuk","My little baby","small","large",wordsByteArray[25]))
        words.add(WordObject("adjectives","big","buyuk","My big home","large","small",wordsByteArray[26]))
        words.add(WordObject("adjectives","old","yaslı","My old grandfather","aged","young",wordsByteArray[27]))
        words.add(WordObject("adjectives","young","genc","My young friend","juniour","old",wordsByteArray[28]))
        words.add(WordObject("adjectives","small","kucuk","My small table","little","big",wordsByteArray[29]))
        words.add(WordObject("phrasesAndIdioms","A chip on your shoulder","Gecmisten bir seye kizgin olmak","A chip on your shoulder","arrive","leave",wordsByteArray[30]))
        words.add(WordObject("phrasesAndIdioms","A pice of Cake","Kolayca yapilacak bir is","A pice of Cake","easily","hardly",wordsByteArray[31]))
        words.add(WordObject("phrasesAndIdioms","An arm and a leg","Odenmesi gerekenden pahali","An arm and a leg","too expensive","too chip",wordsByteArray[32]))
        words.add(WordObject("phrasesAndIdioms","Back to square one","Basa donmek","Back to square one","return the beginning","return the finnish point",wordsByteArray[33]))
        words.add(WordObject("phrasesAndIdioms","Barking up the wrong","Yanlis varsayimda bulunmak","Barking up the wrong","none","none",wordsByteArray[34]))
        words.add(WordObject("phrasesAndIdioms","Between a rock and attard place","dilemma icinde bulunmak","Between a rock and attard place","dilemma","none",wordsByteArray[35]))
        words.add(WordObject("phrasesAndIdioms","Burst your bubble","Mutlu anini yok etmek","Burst your bubble","sadness in the happiness","happiness in the sadness",wordsByteArray[36]))
        words.add(WordObject("phrasesAndIdioms","Curiosity killed the cat","Kendi işine bakmak","Curiosity killed the cat","look at your business","looking everyone business",wordsByteArray[37]))
        words.add(WordObject("phrasesAndIdioms","Cut to the chase","Gereksiz tüm detayları pas gecmek","Cut to the chase","cutting the detail","telling the all detail",wordsByteArray[38]))
        words.add(WordObject("phrasesAndIdioms","A dime a dozen","Elde etmesi cok kolay","A dime a dozen","easily","hardly",wordsByteArray[39]))









        return words
    }
}