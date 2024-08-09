package com.example.kisileruygulamasisqflite

import android.annotation.SuppressLint
import android.content.ContentValues

class KisilerDao {

     fun kisiSil(vt:VeritabaniYardimcisi,kisiId:Int){
         val db=vt.writableDatabase
         db.delete("kisiler","kisi_id=?", arrayOf(kisiId.toString()))
         db.close()
     }

     fun kisiEkle(vt:VeritabaniYardimcisi,kisiAd:String,kisiTel:String){
         val db=vt.writableDatabase

         val values=ContentValues()
         values.put("kisi_ad",kisiAd)
         values.put("kisi_tel",kisiTel)

         db.insertOrThrow("kisiler",null,values)

         db.close()
     }

    fun kisiGuncelle(vt:VeritabaniYardimcisi,kisiId:Int,kisiAd:String,kisiTel:String){
        val db=vt.writableDatabase

        val values=ContentValues()
        values.put("kisi_ad",kisiAd)
        values.put("kisi_tel",kisiTel)

        db.update("kisiler",values,"kisi_id=?", arrayOf(kisiId.toString()))

        db.close()
    }


    @SuppressLint("Range")
    fun tumKisiler(vt:VeritabaniYardimcisi):ArrayList<Kisiler>{
        val db=vt.writableDatabase
        val kisilerListe=ArrayList<Kisiler>()

        val c=db.rawQuery("SELECT*FROM kisiler",null)

        while (c.moveToNext()){
            val kisi=Kisiler(c.getInt(c.getColumnIndex("kisi_id")),
                c.getString(c.getColumnIndex("kisi_ad")),
                c.getString(c.getColumnIndex("kisi_tel")))

            kisilerListe.add(kisi)
        }
        return  kisilerListe
    }


    @SuppressLint("Range")
    fun kisiAra(vt:VeritabaniYardimcisi, aramaKelimesi:String):ArrayList<Kisiler>{
        val db=vt.writableDatabase
        val kisilerListe=ArrayList<Kisiler>()

        val c=db.rawQuery("SELECT*FROM kisiler WHERE kisi_ad like '%$aramaKelimesi%'",null)

        while (c.moveToNext()){
            val kisi=Kisiler(c.getInt(c.getColumnIndex("kisi_id")),
                c.getString(c.getColumnIndex("kisi_ad")),
                c.getString(c.getColumnIndex("kisi_tel")))

            kisilerListe.add(kisi)
        }
        return  kisilerListe
    }
}