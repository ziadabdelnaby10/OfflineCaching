package com.example.offlinecaching.ui.dessert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.offlinecaching.adapter.DessertOnClickListener
import com.example.offlinecaching.adapter.DessertRecyclerAdapter
import com.example.offlinecaching.databinding.FragmentDessertBinding
import com.example.offlinecaching.model.Dessert
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DessertFragment : Fragment(), DessertOnClickListener {

    private lateinit var _binding: FragmentDessertBinding
    private val viewModel: DessertViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDessertBinding.inflate(inflater, container, false)
        val adapter = DessertRecyclerAdapter(this)
        _binding.viewModel = viewModel
        _binding.lifecycleOwner = viewLifecycleOwner
        _binding.recyclerView.adapter = adapter
        return _binding.root
    }

    override fun onClick(position: Int, item: Dessert) {
        Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
    }
}