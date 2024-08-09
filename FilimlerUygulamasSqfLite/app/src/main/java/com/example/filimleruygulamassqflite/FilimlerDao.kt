package com.example.filimleruygulamassqflite

import android.annotation.SuppressLint

class FilimlerDao {

    @SuppressLint("Range")
    fun tumFilmlerGetirByKategoriId(vt:VeritabaniYardimcisi, kategoriId:Int):ArrayList<Filmler>{

        val db=vt.writableDatabase
        val filmListe=ArrayList<Filmler>()

        val c=db.rawQuery("SELECT*FROM kategoriler,yonetmenler,filmler WHERE filmler.kategori_id=kategoriler.kategori_id  AND filmler.yonetmen_id=yonetmenler.yonetmen_id AND filmler.kategori_id=$kategoriId",null)

        while (c.moveToNext()){
            val kategori=Kategoriler(c.getInt(c.getColumnIndex("kategori_id")),
                c.getString(c.getColumnIndex("kategori_ad")))

            val yonetmen=Yonetmenler(c.getInt(c.getColumnIndex("yonetmen_id")),
                c.getString(c.getColumnIndex("yonetmen_ad")))

            val film=Filmler(c.getInt(c.getColumnIndex("film_id")),
                c.getString(c.getColumnIndex("film_ad")),
                c.getInt(c.getColumnIndex("film_yil")),
                c.getString(c.getColumnIndex("film_resim")),kategori,yonetmen)

            filmListe.add(film)
        }
        return filmListe
    }
}