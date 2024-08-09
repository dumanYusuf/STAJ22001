package com.example.filimleruygulamassqflite

import java.io.Serializable

data class Kategoriler(var kategori_id:Int,var kategori_ad:String):Serializable {

    //Serizable yapmamızın sebebi veri transferi yapıcaz

}