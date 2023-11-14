package com.myapps.wallapers_app.Repository

import com.myapps.wallapers_app.Api.Constants
import retrofit2.http.Query

class WallpaperRepository {

    suspend fun getWallpaper(query: String) = Constants.api.getWallpaper(query)
}