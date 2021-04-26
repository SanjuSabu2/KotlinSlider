package com.example.kotlinslider.api

import com.example.kotlinslider.model.MainModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CartApi {
    @POST("api/v1/banner_images")
    fun getImages(): Call<MainModel>
}