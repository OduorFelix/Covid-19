package com.flx.covid_19.api

import com.flx.covid_19.models.Covid_model
import com.flx.covid_19.response.CovidResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

class RetrofitClient {

    interface ApiInterface {

        @Headers("Content-Type:application/json")
        @GET("api/v1/summary/latest")
        fun fetchData(): Call<CovidResponse>

    }

    class RetrofitInstance {
        companion object {

            private const val BASE_URL: String = "https://api.quarantine.country/"

            private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            private val client: OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()
            fun getRetrofitInstance(): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
    }

}