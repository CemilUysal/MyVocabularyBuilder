package com.example.myvocabularybuilder

class WordObject {
    var id: Int = 0
    var category: String = ""
    var word: String = ""
    var meaning: String = ""
    var sentence: String = ""
    var synonyms: String = ""
    var antonyms: String = ""
    var byteArray: ByteArray =ByteArray(500)

    constructor()
    constructor(category: String, word: String, meaning: String, sentence: String, synonyms: String, antonyms: String,byteArray: ByteArray) {
        this.category = category
        this.word = word
        this.meaning = meaning
        this.sentence = sentence
        this.synonyms = synonyms
        this.antonyms = antonyms
        this.byteArray = byteArray
    }

    constructor(id: Int, word: String) {
        this.id = id
        this.word = word
    }


}