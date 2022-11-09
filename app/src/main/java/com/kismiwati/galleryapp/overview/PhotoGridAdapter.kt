package com.kismiwati.galleryapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kismiwati.galleryapp.databinding.GridViewItemBinding
import com.kismiwati.galleryapp.network.MarsPhoto

/**
 * Bagian ini Kelas akan mengimplementasikan RecyclerView dan ListAdapter yang digunakan Data Binding untuk menampilkan List.
 */
class PhotoGridAdapter :
    ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotosViewHolder>(DiffCallback) {

    /**
     * Bagian ini Konstruktor MarsPhotosViewHolder digunakan untuk mengambil variabel binding.
     * GridViewItem digunakan untuk memberikan akses ke informasi MarsPhoto dengan lengkap.
     */
    class MarsPhotosViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: MarsPhoto) {
            binding.photo = marsPhoto
            // bagian ini diguanakn untuk proses eksekusi,
            // dimana RecyclerView digunakan untuk membuat pengukuran ukuran tampilan.
            binding.executePendingBindings()
        }
    }

    /**
     * Bagian ini RecyclerView digunakan untuk menentukan item mana yang telah berubah ketika [List] dari [MarsPhoto] telah diperbarui.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    /**
     * Bagian ini membuat tampilan item [RecyclerView] baru yang dipanggil oleh pengelola tata letak
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Bagian ini digunakan untuk Mengganti konten tampilan yang dipanggil oleh pengelola tata letak
     */
    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}
