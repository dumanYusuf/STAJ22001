package com.example.filimleruygulamassqflite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filimleruygulamassqflite.databinding.FragmentAnaSayfaBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper


class AnaSayfaFragment : Fragment() {

    private lateinit var kategoriListesi:ArrayList<Kategoriler>
    private lateinit var adepter:kategoriAdepter
    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentAnaSayfaBinding.inflate(inflater,container,false)

        veriTabaniKopyala()

        binding.toolbarKategori.title="Kategoriler"

        binding.kategoriRv.setHasFixedSize(true)
        binding.kategoriRv.layoutManager=LinearLayoutManager(context)

       /* kategoriListesi= ArrayList()

        val f1=Kategoriler(1,"Komedi")
        val f2=Kategoriler(1,"Bilim Kurgu")

        kategoriListesi.add(f1)
        kategoriListesi.add(f2)*/

        vt=VeritabaniYardimcisi(context)
        kategoriListesi=KategorilerDao().tumKategorileriGetir(vt)

        adepter= kategoriAdepter(context,kategoriListesi)
        binding.kategoriRv.adapter=adepter




        return binding.root
    }


    fun veriTabaniKopyala(){
        val copyHelper= DatabaseCopyHelper(context)
        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }
        catch (e:Exception){
            e.printStackTrace()
        }

    }

}