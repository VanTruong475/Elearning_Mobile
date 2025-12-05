package com.example.tuan51

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("tasks")
    suspend fun getTasks(): Response<TasksResponse>
    
    @GET("task/{id}")
    suspend fun getTaskDetail(@Path("id") id: String): Response<Task>
    
    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: String): Response<Unit>
}

object RetrofitClient {
    private const val BASE_URL = "https://amock.io/api/researchUTH/"
    
    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d("API", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .registerTypeAdapter(TasksResponse::class.java, TasksResponseDeserializer())
        .create()
    
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}

