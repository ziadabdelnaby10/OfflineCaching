package com.example.offlinecaching.ui.restaurant

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.offlinecaching.R
import com.example.offlinecaching.databinding.FragmentRestaurantBinding

class RestaurantFragment : Fragment() {

    //private var viewModel: RestaurantViewModel by viewModels()
    lateinit var binding : FragmentRestaurantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestaurantBinding.inflate(inflater , container , false)
        return binding.root
    }

}