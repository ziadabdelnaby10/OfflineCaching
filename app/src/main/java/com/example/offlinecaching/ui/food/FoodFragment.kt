package com.example.offlinecaching.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.offlinecaching.adapter.FoodOnClickListener
import com.example.offlinecaching.adapter.FoodRecyclerAdapter
import com.example.offlinecaching.databinding.FragmentFoodBinding
import com.example.offlinecaching.model.Food
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodFragment : Fragment(), FoodOnClickListener {

    private lateinit var _binding: FragmentFoodBinding
    private val viewModel: FoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        val adapter = FoodRecyclerAdapter(this)
        _binding.viewModel = viewModel
        _binding.lifecycleOwner = viewLifecycleOwner
        _binding.recyclerView.adapter = adapter

        return _binding.root
    }

    override fun onClick(position: Int, item: Food) {
        Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
    }
}