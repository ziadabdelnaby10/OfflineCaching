package com.example.offlinecaching.ui.coffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.offlinecaching.adapter.CoffeeOnClickListener
import com.example.offlinecaching.adapter.CoffeeRecyclerAdapter
import com.example.offlinecaching.databinding.FragmentCoffeeBinding
import com.example.offlinecaching.model.Coffee
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoffeeFragment : Fragment(), CoffeeOnClickListener {

    private lateinit var _binding: FragmentCoffeeBinding
    private val viewModel: CoffeeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeBinding.inflate(inflater, container, false)
        val adapter = CoffeeRecyclerAdapter(this)
        _binding.viewModel = viewModel
        _binding.lifecycleOwner = viewLifecycleOwner
        _binding.recyclerView.adapter = adapter
        return _binding.root
    }

    override fun onClick(position: Int, item: Coffee) {
        Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
    }
}