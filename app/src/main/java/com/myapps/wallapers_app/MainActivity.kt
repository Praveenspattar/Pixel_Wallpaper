package com.myapps.wallapers_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapps.pixel_wallpapers_app.R
import com.myapps.pixel_wallpapers_app.databinding.ActivityMainBinding
import com.myapps.wallapers_app.Adapter.WallAdapter
import com.myapps.wallapers_app.Adapter.WallTopAdapter
import com.myapps.wallapers_app.Models.Photo
import com.myapps.wallapers_app.Models.TopPhotos
import com.myapps.wallapers_app.Repository.WallpaperRepository
import com.myapps.wallapers_app.ViewModelFactory.ViewModel
import com.myapps.wallapers_app.ViewModelFactory.WallpaperViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : ViewModel
    lateinit var wallAdapter: WallAdapter
    lateinit var binding : ActivityMainBinding

    //top recyclerView
    lateinit var wallTopAdapter: WallTopAdapter
    private lateinit var topPhotosArrayList: ArrayList<TopPhotos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topPhotosArrayList = arrayListOf<TopPhotos>()

        topPhotosArrayList.add(TopPhotos(R.drawable.krishna,"krishna"))
        topPhotosArrayList.add(TopPhotos(R.drawable.car,"car"))
        topPhotosArrayList.add(TopPhotos(R.drawable.model,"model"))
        topPhotosArrayList.add(TopPhotos(R.drawable.love,"love"))
        topPhotosArrayList.add(TopPhotos(R.drawable.programmer,"programmer"))
        topPhotosArrayList.add(TopPhotos(R.drawable.android,"android"))
        topPhotosArrayList.add(TopPhotos(R.drawable.game,"game"))
        topPhotosArrayList.add(TopPhotos(R.drawable.fashion,"fashion"))
        topPhotosArrayList.add(TopPhotos(R.drawable.friends,"friends"))
        topPhotosArrayList.add(TopPhotos(R.drawable.building,"building"))

        binding.button.setOnClickListener{
            val searched = binding.editTextTextPersonName.text.toString()

            val result = viewModel.getWallpaper(searched)
            Log.d("praveen","OnCreateView: ${result}")
        }

        val repository = WallpaperRepository()
        val viewmodelfactory = WallpaperViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewmodelfactory).get(ViewModel::class.java)

        //top recyclerView
        //wallTopAdapter.setTopWalpaperData(topPhotosArrayList,this)
        setupTopRecyclerView()

        setupRecyclerView()

        viewModel.setDefaultWallpaper()

        viewModel.wallpaperlist.observe(this, Observer {praveen->
            if(praveen.isSuccessful){
                val response = praveen.body()

                if (response == null){

                }else{
                    wallAdapter.setWalpaperData(response.photos as ArrayList<Photo>,this)
                }
            }
        })
    }

    private fun setupTopRecyclerView() {
//        for (img in imgId){
//            val image = TopPhotos(img)
//            topPhotosArrayList.add(image)
//        }

        wallTopAdapter = WallTopAdapter()
        binding.recyclerViewTop.apply {
            adapter = wallTopAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
        }
        wallTopAdapter.setTopWalpaperData(topPhotosArrayList,this)
    }

    private fun setupRecyclerView(){
        wallAdapter = WallAdapter()
        binding.recyclerView.apply {

            adapter= wallAdapter
            layoutManager = GridLayoutManager(this@MainActivity,2)

        }
    }
}