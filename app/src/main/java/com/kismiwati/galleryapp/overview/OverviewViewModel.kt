package com.kismiwati.galleryapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kismiwati.galleryapp.network.MarsApi
import com.kismiwati.galleryapp.network.MarsPhoto
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

/**
 * Bagian ini [ViewModel] akan dilampirkan ke [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // MutableLiveData internal digunakan untuk menyimpan status permintaan terbaru
    private val _status = MutableLiveData<MarsApiStatus>()

    // LiveData eksternal bagian ini tidak dapat diubah untuk status permintaan
    val status: LiveData<MarsApiStatus> = _status

    // bagian ini menggunakan MutableLiveData, karena agar dapat memperbarui Daftar Foto Mars
    private val _photos = MutableLiveData<List<MarsPhoto>>()

    // Bagian antarmuka LiveData eksternal ke properti tidak dapat diubah, jadi hanya kelas ini yang dapat memodifikasi
    val photos: LiveData<List<MarsPhoto>> = _photos

    /**
     * Bagian ini digunakan untuk memanggil getMarsPhotos() pada init sehingga  dapat segera menampilkan status.
     */
    init {
        getMarsPhotos()
    }

    /**
     * bagian ini digunakan untuk mendapatkan informasi foto Mars dari layanan Mars API Retrofit dan memperbarui dari [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {

        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}
