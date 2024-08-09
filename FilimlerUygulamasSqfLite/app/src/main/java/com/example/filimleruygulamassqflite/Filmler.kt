package com.example.filimleruygulamassqflite

import java.io.Serializable

data class Filmler(
    var film_id:Int,
    var film_ad:String,
    var film_yil:Int,
    var film_resim:String,
    var kategori:Kategoriler,//Burda composision gercekleştirdik başka sınıfın nensesini bu sınıfa vererek ordaki özeliklerin bu sınıfta kullanılmasını sagladık
    var yonetmen:Yonetmenler
    ):Serializable {
}