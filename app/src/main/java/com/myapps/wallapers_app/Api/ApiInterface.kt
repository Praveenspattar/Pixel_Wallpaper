package com.myapps.wallapers_app.Api

import com.myapps.wallapers_app.Models.WallPaperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    //api key UOJsKQSWh2xGS3sCxxDssawWWAU10D3USTBeV0vjLf1RC6CnmmG79mQz
    @Headers("Authorization: UOJsKQSWh2xGS3sCxxDssawWWAU10D3USTBeV0vjLf1RC6CnmmG79mQz")
    @GET("search")
    suspend fun getWallpaper(
        @Query("query") query: String =" nature",
        @Query("perpage") perpage : Int = 80
    ): Response<WallPaperResponse>
}