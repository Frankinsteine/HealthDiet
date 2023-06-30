package com.example.healthdiet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthdiet.R
import com.example.healthdiet.adapters.DietListAdapter
import com.example.healthdiet.adapters.RecipeAdapter
import com.example.healthdiet.databinding.FragmentChooseDietBinding
import com.example.healthdiet.databinding.FragmentHomeBinding
import com.example.healthdiet.models.DietButton
import com.example.healthdiet.viewmodels.RecipeViewModel


class chooseDiet : Fragment() {
    private lateinit var binding: FragmentChooseDietBinding
    private lateinit var adapter: DietListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseDietBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = DietListAdapter(requireActivity())
        binding.recyclerView.adapter = adapter

        var data = mutableListOf<DietButton>()

        for (i in 1..15) {
            val item = DietButton(i, "Стол №${i}")
            data.add(item)
        }

        adapter.setListData(data)

        return binding.root
    }
}