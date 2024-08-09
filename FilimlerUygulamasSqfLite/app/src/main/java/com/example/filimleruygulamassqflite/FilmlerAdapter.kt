package com.example.filimleruygulamassqflite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class FilmlerAdapter(private val mcontext: Context?, private val fillerListesi:List<Filmler>)
    :RecyclerView.Adapter<FilmlerAdapter.CardTasarimNesneleriTutucu>()
{

    //Bu adapter sınıfı veri kümesini alarak recylercy view üzerinde gösterecek

    inner class CardTasarimNesneleriTutucu(tasarim: View): RecyclerView.ViewHolder(tasarim){// olusturdugumuz card tasarım görsenllerine tutar ve ıd leri sayesinde erişiriz ve bu sınıfta kullanıtız

        var filimlerCard: CardView
        var imageViewFilmResim: ImageView
        var textViewFilmAd:TextView

        init {
            filimlerCard=tasarim.findViewById(R.id.filimlerCard)
            imageViewFilmResim=tasarim.findViewById(R.id.imageViewFilmResim)
            textViewFilmAd=tasarim.findViewById(R.id.textViewFilmAd)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimNesneleriTutucu {
        //Bu metot tasarımı baglayacagımız metot,o yuzden tasarıma ulasıp  nesnesine atamamız lazım
        val tasarim=
            LayoutInflater.from(mcontext).inflate(R.layout.filimler_card_tasarim,parent,false)
        return CardTasarimNesneleriTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return fillerListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimNesneleriTutucu, position: Int) {

        val film=fillerListesi.get(position)

         holder.textViewFilmAd.text=film.film_ad
         holder.imageViewFilmResim.setImageResource(mcontext!!.resources.getIdentifier(film.film_resim,
             "drawable",mcontext.packageName))

        holder.filimlerCard.setOnClickListener {

            val bundle = Bundle()
            bundle.putSerializable("filmNesnesi", film)
            Navigation.findNavController(it)
                .navigate(R.id.action_filimlerSayfaFragment_to_detaySayfaFragment, bundle)

        }

    }

}