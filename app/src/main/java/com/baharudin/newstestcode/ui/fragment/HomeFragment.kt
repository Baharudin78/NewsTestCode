package com.baharudin.newstestcode.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.newstestcode.R
import com.baharudin.newstestcode.adapter.CategoryAdapter
import com.baharudin.newstestcode.databinding.FragmentHomeBinding
import com.baharudin.newstestcode.ui.NewViewModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.baharudin.newstestcode.util.Result
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val newsViewModel : NewViewModel by activityViewModels()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        val imageSlide = ArrayList<SlideModel>()
        imageSlide.add(SlideModel(R.drawable.imafess))
        imageSlide.add(SlideModel(R.drawable.imafess))
        imageSlide.add(SlideModel(R.drawable.imafess))
        imageSlide.add(SlideModel(R.drawable.imafess))
        binding.ivSlider.setImageList(imageSlide, ScaleTypes.FIT)

        setupRecycleview()
        getHealthData()

    }
    private fun getHealthData() {
        newsViewModel.getHealthCategory.observe(viewLifecycleOwner, {response ->
            when(response) {
                is Result.Sucess -> {
                    response.data?.let { result ->
                        setupRecycleview()
                        categoryAdapter.differ.submitList(result.articles)
                    }
                }
                is Result.Error -> {
                    response.message?.let{
                        Log.e("Error", response.message)
                    }
                }
                is Result.Loading -> {
                }
            }
        })
    }

    private fun setupRecycleview() {
        categoryAdapter = CategoryAdapter()
        categoryAdapter.setOnclickListener {
            var action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        binding.rvRekomendasi.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}