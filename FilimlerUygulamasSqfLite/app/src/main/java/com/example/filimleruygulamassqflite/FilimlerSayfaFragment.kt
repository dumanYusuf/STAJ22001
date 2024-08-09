package com.example.filimleruygulamassqflite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.filimleruygulamassqflite.databinding.FragmentFilimlerSayfaBinding

class FilimlerSayfaFragment : Fragment() {

    private lateinit var filmlerListesi:ArrayList<Filmler>
    private lateinit var adepter:FilmlerAdapter
    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentFilimlerSayfaBinding.inflate(inflater,container,false)

        val kategori = arguments?.getSerializable("kategoriNesnesi") as Kategoriler


        binding.toolbarFilmler.title="Filimler:${kategori.kategori_ad}"
        binding.filimlerRv.setHasFixedSize(true)
        binding.filimlerRv.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

       /* filmlerListesi=ArrayList()

        val k=Kategoriler(1,"Dram")
        val y=Yonetmenler(1,"Yusuf Duman")

        val f1=Filmler(1,"Django",2008,"django",k,y)
        val f2=Filmler(2,"Inception",2010,"inception",k,y)
        val f3=Filmler(3,"The Pianist",2008,"thepianist",k,y)

        filmlerListesi.add(f1)
        filmlerListesi.add(f2)
        filmlerListesi.add(f3)
*/

        vt= VeritabaniYardimcisi(context)

        filmlerListesi=FilimlerDao().tumFilmlerGetirByKategoriId(vt,kategori.kategori_id    )

        adepter= FilmlerAdapter(context,filmlerListesi)
        binding.filimlerRv.adapter=adepter


        return binding.root
    }


}