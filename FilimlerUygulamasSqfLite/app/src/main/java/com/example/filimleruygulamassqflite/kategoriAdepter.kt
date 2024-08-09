package com.example.filimleruygulamassqflite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Layout.Directions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.filimleruygulamassqflite.databinding.FragmentFilimlerSayfaBinding

class kategoriAdepter(private val mcontext: Context?, private val liste:List<Kategoriler>)
    :RecyclerView.Adapter<kategoriAdepter.CardTasarimNesneleriTutucu>()
{

    //Bu adapter sınıfı veri kümesini alarak recylercy view üzerinde gösterecek

    inner class CardTasarimNesneleriTutucu(tasarim: View):RecyclerView.ViewHolder(tasarim){// olusturdugumuz card tasarım görsenllerine tutar ve ıd leri sayesinde erişiriz ve bu sınıfta kullanıtız

        var kategori_card:CardView
        var textViewKategoriAd:TextView

        init {
             kategori_card=tasarim.findViewById(R.id.kategori_card)
             textViewKategoriAd=tasarim.findViewById(R.id.textViewKategoriAd)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimNesneleriTutucu {
        //Bu metot tasarımı baglayacagımız metot,o yuzden tasarıma ulasıp  nesnesine atamamız lazım
        val tasarim=LayoutInflater.from(mcontext).inflate(R.layout.kategori_card_tasarim,parent,false)
        return CardTasarimNesneleriTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: CardTasarimNesneleriTutucu, position: Int) {

           val kategori=liste.get(position)

        holder.textViewKategoriAd.text=kategori.kategori_ad
        holder.kategori_card.setOnClickListener {

            val bundle= Bundle()
            bundle.putSerializable("kategoriNesnesi",kategori)
            Navigation.findNavController(it).navigate(R.id.action_anaSayfaFragment_to_filimlerSayfaFragment,bundle)
        }

    }
}