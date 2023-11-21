package com.myapps.wallapers_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.myapps.pixel_wallpapers_app.databinding.ActivityMain2Binding
import com.myapps.wallapers_app.Adapter.WallAdapter2
import com.myapps.wallapers_app.Models.Photo
import com.myapps.wallapers_app.Repository.WallpaperRepository
import com.myapps.wallapers_app.ViewModelFactory.ViewModel
import com.myapps.wallapers_app.ViewModelFactory.WallpaperViewModelFactory

class MainActivity2 : AppCompatActivity() {

    lateinit var wallAdapter2: WallAdapter2
    lateinit var viewModel: ViewModel
    lateinit var binding : ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.getStringExtra("name")
        Log.d("lh", "onCreate: ${intent.toString()}")

        val repository = WallpaperRepository()
        val viewModelFactory = WallpaperViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ViewModel::class.java)

        viewModel.getWallpaper(intent!!.toString())

        viewModel.wallpaperlist.observe(this,{
            val response = it.body()
            if (it.isSuccessful){
                wallAdapter2.setData2(response!!.photos as ArrayList<Photo>, this)
            }
        })

        setupRecyclerView()

    }

    fun setupRecyclerView(){
        wallAdapter2 = WallAdapter2()

        binding.recyclerView2.apply {
            adapter = wallAdapter2
            layoutManager = GridLayoutManager(this@MainActivity2,2)
        }
    }

}