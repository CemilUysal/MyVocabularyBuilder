package com.example.myvocabularybuilder

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

class BitmapFixer {
    fun fixedTheBitmap(bitmap: Bitmap, maxWeight: Int): Bitmap? {
        var width = bitmap.width
        var height = bitmap.height
        val bitmapOrani = width.toDouble() / height.toDouble()
        if(bitmapOrani > 1){
            width = maxWeight
            val newheight = width / bitmapOrani
            height = newheight.toInt()

        }
        else{
            height = maxWeight
            val newwidth = height / bitmapOrani
            width = newwidth.toInt()

        }
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }
    fun BitmaptoByteArray(bitmap: Bitmap): ByteArray{
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,50, outputStream)
        val ByteDizisi = outputStream.toByteArray()
        return ByteDizisi
    }
}