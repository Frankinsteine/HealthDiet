package com.example.healthdiet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthdiet.R
import com.example.healthdiet.adapters.ProductAdapter
import com.example.healthdiet.adapters.RecipeStepsAdapter
import com.example.healthdiet.databinding.FragmentDescWithBtnBinding
import com.example.healthdiet.databinding.FragmentEventDetailBinding
import com.example.healthdiet.viewmodels.RecipeViewModel
import com.squareup.picasso.Picasso

class descWithBtn : Fragment() {

    private lateinit var binding: FragmentDescWithBtnBinding
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescWithBtnBinding.inflate(inflater,container,false)

        val args = descWithBtnArgs.fromBundle(requireArguments())
        val item = args.diet
        val flag = args.flag

        if (flag == 1) {
            binding.descTv.text = "Разрешённые продукты: "

            binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
            adapter = ProductAdapter(requireActivity())
            binding.recyclerView.adapter = adapter

            adapter.setListData(item.greenList)
        } else if (flag == 2) {
            binding.descTv.text = "Запрещённые продукты: "

            binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
            adapter = ProductAdapter(requireActivity())
            binding.recyclerView.adapter = adapter

            adapter.setListData(item.redList)
        }


        return binding.root
    }

}