package com.kismiwati.galleryapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kismiwati.galleryapp.network.MarsPhoto
import com.kismiwati.galleryapp.overview.MarsApiStatus
import com.kismiwati.galleryapp.overview.PhotoGridAdapter

/**
 * Bagian ini digunakan untuk memperbarui data yang ditampilkan di RecyclerView.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

/**
 * Bagian ini digunakan perpustakaan untuk memuat gambar dengan URL ke dalam ImageView
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}


 //Bagian ini Adaptor akan menampilkan MarsApiStatus dari permintaan jaringan dalam tampilan gambar.
 //ketika permintaan sedang memuat, maka akan menampilkan loading_animation.
//Jika permintaan memiliki kesalahan, maka akan menampilkan gambar yang rusak untuk memberitahu terjadi kesalahan koneksi.
 //Ketika permintaan selesai, maka akan menyembunyikan tampilan gambar.

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
