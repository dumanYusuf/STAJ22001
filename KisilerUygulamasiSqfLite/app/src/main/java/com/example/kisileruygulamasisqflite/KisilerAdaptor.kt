package com.example.kisileruygulamasisqflite

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class KisilerAdapter(
    private val mcontext:Context,
    private var kisilerListesi:List<Kisiler>,
    private val vt:VeritabaniYardimcisi)
    :RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu>(){


    inner class CardTasarimTutucu(tasarim:View):RecyclerView.ViewHolder(tasarim){
        var textviewKisiBilgi:TextView
        var imageView:ImageView

        init {
            textviewKisiBilgi=tasarim.findViewById(R.id.textViewKisiBilgi)
            imageView=tasarim.findViewById(R.id.imageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim=LayoutInflater.from(mcontext).inflate(R.layout.kisi_card_tasarim,parent,false)
        return  CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
       return kisilerListesi.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        val kisi=kisilerListesi.get(position)

        holder.textviewKisiBilgi.text="${kisi.kisi_ad}-${kisi.kisi_tel}"

        holder.imageView.setOnClickListener{
                 val popUpMenu=PopupMenu(mcontext,holder.imageView)
                 popUpMenu.menuInflater.inflate(R.menu.menu_popup,popUpMenu.menu)

                 popUpMenu.setOnMenuItemClickListener { menuItem->
                        when(menuItem.itemId){
                            R.id.action_sil->{
                                Snackbar.make(holder.imageView,"${kisi.kisi_ad} Silinsin mi?",Snackbar.LENGTH_LONG)
                                    .setAction("Evet"){
                                       KisilerDao().kisiSil(vt,kisi.kisi_id)
                                        kisilerListesi=KisilerDao().tumKisiler(vt)
                                        notifyDataSetChanged()
                                    }.show()
                                true
                            }
                            R.id.action_guncelle->{
                                alertGoster(kisi)
                                true
                            }
                            else-> {
                                false
                            }
                        }
                 }

                 popUpMenu.show()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun alertGoster(kisi:Kisiler){
        val tasarim=LayoutInflater.from(mcontext).inflate(R.layout.alert_tasarim,null)
        val editTextAd=tasarim.findViewById(R.id.editTextTextAd) as EditText
        val editTextTel=tasarim.findViewById(R.id.editTextTextTeliniz) as EditText

        editTextAd.setText(kisi.kisi_ad)
        editTextTel.setText(kisi.kisi_tel)

        val ad= AlertDialog.Builder(mcontext)

        ad.setTitle("Kişi Güncelle")
        ad.setView(tasarim)
        ad.setPositiveButton("Güncelle"){dialogInterface,i->
            val kisiAd=editTextAd.text.toString().trim()
            val kisiTel=editTextTel.text.toString().trim()

            KisilerDao().kisiGuncelle(vt,kisi.kisi_id,kisiAd,kisiTel)
            kisilerListesi=KisilerDao().tumKisiler(vt)
            notifyDataSetChanged()

            Toast.makeText(mcontext,"${kisiAd}-${kisiTel}", Toast.LENGTH_SHORT).show()

        }
        ad.setNegativeButton("İptal"){dialogInterface,i->
        }

        ad.create().show()

    }
}