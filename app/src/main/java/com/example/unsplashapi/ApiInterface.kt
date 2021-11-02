package com.example.unsplashapi

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("photos/random?count=16&client_id=_i9RzQd9PX8-RqoxAOfopM_ai1Oqeqh-K7-NyLaYsLw")
    fun getData(): Call<List<MyDataItem>>
}