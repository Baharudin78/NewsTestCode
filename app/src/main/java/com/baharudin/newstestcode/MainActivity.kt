package com.baharudin.newstestcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharudin.newstestcode.databinding.ActivityMainBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageSlide = ArrayList<SlideModel>()
        imageSlide.add(SlideModel(R.drawable.imafess))
        imageSlide.add(SlideModel(R.drawable.imafess))
        imageSlide.add(SlideModel(R.drawable.imafess))
        imageSlide.add(SlideModel(R.drawable.imafess))
        binding.ivSlider.setImageList(imageSlide,ScaleTypes.FIT)
    }

}