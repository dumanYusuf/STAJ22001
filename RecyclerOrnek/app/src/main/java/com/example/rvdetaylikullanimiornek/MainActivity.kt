package com.example.rvdetaylikullanimiornek

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rvdetaylikullanimiornek.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var filmlerArrayList: ArrayList<Film>
    private lateinit var adapter: FilimAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val f1 = Film(1, "Django", "django", 15.78)
        val f2 = Film(2, "Inception", "inception", 25.78)
        val f3 = Film(3, "Interstellar", "interstellar", 10.89)
        val f4 = Film(4, "Hateful Eight", "thehatefuleight", 15.65)
        val f5 = Film(5, "The Pianist", "thepianist", 17.78)
        val f6 = Film(6, "Anadolu'da", "birzamanlaranadoluda", 5.78)

        filmlerArrayList = ArrayList()
        filmlerArrayList.add(f1)
        filmlerArrayList.add(f2)
        filmlerArrayList.add(f3)
        filmlerArrayList.add(f4)
        filmlerArrayList.add(f5)
        filmlerArrayList.add(f6)

        adapter = FilimAdapter(this, filmlerArrayList)
        binding.rv.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
