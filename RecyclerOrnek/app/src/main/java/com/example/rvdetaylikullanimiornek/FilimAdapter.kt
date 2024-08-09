package com.example.rvdetaylikullanimiornek

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FilimAdapter(private val mcontext: Context, private val filimlerListesi: List<Film>)
    : RecyclerView.Adapter<FilimAdapter.CardTasarimNesneTututcu>() {

    inner class CardTasarimNesneTututcu(view: View) : RecyclerView.ViewHolder(view) {
        var imageViewFilmResim: ImageView = view.findViewById(R.id.imageViewFilmResim)
        var textViewFilmBaslik: TextView = view.findViewById(R.id.textViewFilmBaslik)
        var textViewFilmFiyat: TextView = view.findViewById(R.id.textViewFilmFiyat)
        var buttonSepeteEkle: Button = view.findViewById(R.id.buttonSepeteEkle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimNesneTututcu {
        val tasarim = LayoutInflater.from(mcontext).inflate(R.layout.card_film_tasarim, parent, false)
        return CardTasarimNesneTututcu(tasarim)
    }

    override fun getItemCount(): Int {
        return filimlerListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimNesneTututcu, position: Int) {
        val filim = filimlerListesi[position]

        holder.textViewFilmBaslik.text = filim.filmAdi
        holder.textViewFilmFiyat.text = "${filim.filmFiyat} TL"

        val resimKaynakId = mcontext.resources.getIdentifier(
            filim.resimAdi, "drawable", mcontext.packageName
        )
        if (resimKaynakId != 0) {
            holder.imageViewFilmResim.setImageResource(resimKaynakId)
        } else {
            // Resim bulunamadığında varsayılan bir resim gösterme
            holder.imageViewFilmResim.setImageResource(R.drawable.django)
        }

        holder.buttonSepeteEkle.setOnClickListener {
            Toast.makeText(mcontext, "${filim.filmAdi} sepete eklendi", Toast.LENGTH_SHORT).show()
        }
    }
}
