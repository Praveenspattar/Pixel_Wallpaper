package com.myapps.wallapers_app.ViewModelFactory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.wallapers_app.Models.WallPaperResponse
import com.myapps.wallapers_app.Repository.WallpaperRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel(val repository: WallpaperRepository):ViewModel() {
    lateinit var wallpaperlist : MutableLiveData<Response<WallPaperResponse>>

    init {
        wallpaperlist = MutableLiveData()
        getWallpaper("nature")
    }

    fun getWallpaper(s: String){
        viewModelScope.launch {
            val response = repository.getWallpaper(s)
            wallpaperlist.postValue(response)

        }
    }
}