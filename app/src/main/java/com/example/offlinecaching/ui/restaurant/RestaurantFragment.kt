package com.example.offlinecaching.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.offlinecaching.adapter.RestaurantOnClickListener
import com.example.offlinecaching.adapter.RestaurantRecyclerAdapter
import com.example.offlinecaching.databinding.FragmentRestaurantBinding
import com.example.offlinecaching.model.Restaurant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment(), RestaurantOnClickListener {

    private val viewModel: RestaurantViewModel by viewModels()
    private lateinit var _binding: FragmentRestaurantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        val adapter = RestaurantRecyclerAdapter(this)
        _binding.viewModel = viewModel
        _binding.lifecycleOwner = viewLifecycleOwner
        _binding.recyclerView.adapter = adapter
        return _binding.root
    }

    override fun onClick(position: Int, item: Restaurant) {
        Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
    }

}