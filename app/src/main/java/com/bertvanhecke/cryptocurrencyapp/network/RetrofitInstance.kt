package com.bertvanhecke.cryptocurrencyapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import com.bertvanhecke.cryptocurrencyapp.constants.Constants.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(logging).build()

            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).client(client).build()
        }

        val api: MessariApi by lazy {
            retrofit.create(MessariApi::class.java)
        }
    }

}