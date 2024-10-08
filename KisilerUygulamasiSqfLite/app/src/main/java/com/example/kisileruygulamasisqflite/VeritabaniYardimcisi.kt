package com.example.kisileruygulamasisqflite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeritabaniYardimcisi(context:Context):SQLiteOpenHelper(context,"rehber.sqlite",null,1) {


    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(
            "CREATE TABLE \"kisiler\" (\n" +
                    "\t\"kisi_id\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"kisi_ad\" TEXT,\n" +
                    "\t\"kisi_tel\" TEXT\n" +
                    ")"
        )


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS kisiler ")
        onCreate(db)
    }
}