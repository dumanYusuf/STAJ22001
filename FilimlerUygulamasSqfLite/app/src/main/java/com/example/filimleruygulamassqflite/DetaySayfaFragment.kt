package com.example.filimleruygulamassqflite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filimleruygulamassqflite.databinding.FragmentDetaySayfaBinding


class DetaySayfaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentDetaySayfaBinding.inflate(inflater,container,false)

        val film = arguments?.getSerializable("filmNesnesi") as Filmler

        if(film != null){
            binding.textViewDetayFilmAdi.text=film.film_ad
            binding.textViewDetayFilmYil.text=film.film_yil.toString()
            binding.textViewDetayYonetmen.text=film.yonetmen.yonetmen_ad
            binding.imageViewDetayResim.setImageResource(resources.getIdentifier(film.film_resim,
                "drawable",requireContext().packageName))
        }
        else{
            Log.e("dsa","hata cıktı")
        }

        return binding.root
    }


}