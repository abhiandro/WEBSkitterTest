package com.eiipl.livestock.Network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestManager {

    companion object{
        private var aPIService: ApiService ? = null
        private val ourInstance = RestManager()
        fun getInstance(): RestManager? {
            return ourInstance
        }

        fun getService(): ApiService? {
            if (aPIService == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val httpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(500, TimeUnit.SECONDS)
                    .readTimeout(500, TimeUnit.SECONDS)
                    .writeTimeout(500, TimeUnit.SECONDS)
                    .build()
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Constant.BaseUrl)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                aPIService = retrofit.create(ApiService::class.java)
            }
            return aPIService
        }


    }




}