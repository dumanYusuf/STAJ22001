package com.example.kisileruygulamasisqflite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kisileruygulamasisqflite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,SearchView.OnQueryTextListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var kisilerListesi:ArrayList<Kisiler>
    private lateinit var adepter:KisilerAdapter

    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toolbar.title="Kişiler Uygulaması"
        setSupportActionBar(binding.toolbar)


        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager=LinearLayoutManager(this)


        vt = VeritabaniYardimcisi(this)
        tumKisilerAl()

        binding.fab.setOnClickListener {

            alertGoster()

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        val item=menu?.findItem(R.id.action_ara)
        val searchView=item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return  super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        aramaYap(query)
        Log.e("Gonderilen arama",query)
       return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        aramaYap(newText)
        Log.e("Harf Girdikce",newText)
      return  true
    }




    fun alertGoster(){
        val tasarim=LayoutInflater.from(this).inflate(R.layout.alert_tasarim,null)
        val editTextAd=tasarim.findViewById(R.id.editTextTextAd) as EditText
        val editTextTel=tasarim.findViewById(R.id.editTextTextTeliniz) as EditText

        val ad=AlertDialog.Builder(this)


        ad.setTitle("Kişi Ekle")
        ad.setView(tasarim)
        ad.setPositiveButton("Ekle"){dialogInterface,i->
            val kisiAd=editTextAd.text.toString().trim()
            val kisiTel=editTextTel.text.toString().trim()

            KisilerDao().kisiEkle(vt, kisiAd,kisiTel)
            tumKisilerAl()

            Toast.makeText(applicationContext,"${kisiAd}-${kisiTel}",Toast.LENGTH_SHORT).show()

        }
        ad.setNegativeButton("İptal"){dialogInterface,i->


        }

        ad.create().show()

    }

    fun tumKisilerAl(){
        kisilerListesi=KisilerDao().tumKisiler(vt)

        adepter=KisilerAdapter(this,kisilerListesi,vt)
        binding.rv.adapter=adepter

    }

    fun aramaYap(arama:String){
        kisilerListesi=KisilerDao().kisiAra(vt,arama)

        adepter=KisilerAdapter(this,kisilerListesi,vt)
        binding.rv.adapter=adepter

    }



}