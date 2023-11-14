package com.myapps.wallapers_app.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapps.wallapers_app.Repository.WallpaperRepository

class WallpaperViewModelFactory(private val wallpaperRepository: WallpaperRepository ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return com.myapps.wallapers_app.ViewModelFactory.ViewModel(wallpaperRepository) as T
    }
}