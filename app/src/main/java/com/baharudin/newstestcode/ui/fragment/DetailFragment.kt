package com.baharudin.newstestcode.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.RoundedCorner
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.baharudin.newstestcode.R
import com.baharudin.newstestcode.databinding.FragmentDetailBinding
import com.baharudin.newstestcode.ui.NewViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    val args by navArgs<DetailFragmentArgs>()
    private val newsViewModel : NewViewModel by activityViewModels()
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        getData()

    }

    private fun getData() {
        val news = args.article
        binding.apply {
            Glide.with(this@DetailFragment)
                .load(news.urlToImage)
                .centerCrop()
                .into(ivPoster)
            tvJudul.text = news.title
            tvTanggal.text = news.publishedAt
            tvSumber.text = news.source.name
            tvDesc.text = news.content

        }
    }
}