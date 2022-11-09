package com.kismiwati.galleryapp.network

import com.squareup.moshi.Json

/**
 * Bagian ini adalah Kelas data yang mendefinisikan foto Mars yang menyertakan ID, dan URL gambar.
 * Nama properti dari kelas data ini digunakan oleh Moshi untuk mencocokkan nama nilai di JSON.
 */
data class MarsPhoto(
        val id: String,
        // Bagian ini digunakan untuk memetakan img_src dari JSON ke imgSrcUrl.
        @Json(name = "img_src") val imgSrcUrl: String
)