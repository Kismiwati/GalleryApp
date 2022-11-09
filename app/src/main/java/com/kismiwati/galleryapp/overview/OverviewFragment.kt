package com.kismiwati.galleryapp.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kismiwati.galleryapp.databinding.FragmentOverviewBinding

/**
 * Bagian ini, Fragmen akan menunjukkan status transaksi layanan web foto Mars.
 */
class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    /**
     * Bagian ini digunakan untuk Mengembang tata letak dengan Data Binding, menetapkan pemilik siklus hidupnya ke OverviewFragment
     * Bagian ini juga digunakan untuk mengaktifkan Pengikatan Data untuk mengamati LiveData, dan menyiapkan RecyclerView dengan adaptor.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Bagian ini yaitu Pengikatan Data yang digunakan untuk Mengamati LiveData dengan siklus hidup Fragmen.
        binding.lifecycleOwner = this

        // Bagian ini digunakan untuk Memberikan akses mengikat ke OverviewViewModel
        binding.viewModel = viewModel

        // Bagian ini digunakan untuk Menyetel adaptor photosGrid RecyclerView
        binding.photosGrid.adapter = PhotoGridAdapter()

        return binding.root
    }
}
