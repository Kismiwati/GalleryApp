package com.kismiwati.galleryapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/**
 * Bagian ini digunakan untuk membangun objek Moshi yang akan digunakan Retrofit, untuk menambahkan adaptor Kotlin untuk kompatibilitas Kotlin.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Bagian ini digunakan untuk membuat objek retrofit menggunakan konverter Moshi dengan Moshi.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Bagian Antarmuka publik akan memperlihatkan metode [getPhotos]
 */
interface MarsApiService {
    /**
     * Bagian ini digunakan untuk Mengembalikan [List] dari [MarsPhoto] dan metode  dapat dipanggil dari Coroutine.
     * Anotasi @GET menunjukkan bahwa titik akhir "photos" akan diminta dengan GET
     * Bagian ini juga menggunakan Metode HTTP
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

/**
 * Bagian ini Objek Api publik yang mengekspos layanan Retrofit yang diinisialisasi dengan malas
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
