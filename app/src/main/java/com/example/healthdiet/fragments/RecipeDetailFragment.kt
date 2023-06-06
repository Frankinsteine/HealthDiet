package com.example.healthdiet.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthdiet.adapters.RecipeAdapter
import com.example.healthdiet.adapters.RecipeStepsAdapter
import com.example.healthdiet.databinding.FragmentEventDetailBinding
import com.example.healthdiet.viewmodels.RecipeViewModel
import com.squareup.picasso.Picasso

class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailBinding
    private val viewModel by lazy { ViewModelProvider(this).get(RecipeViewModel::class.java) }
    private lateinit var adapter: RecipeStepsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailBinding.inflate(inflater,container,false)

        binding.progressLayout.visibility = View.VISIBLE

        val args = RecipeDetailFragmentArgs.fromBundle(requireArguments())
        val item = args.recipeItem

        binding.titleTextView.text = item!!.title
        binding.ingredientsTextView.text = item.time
        binding.dateTextView.text = item.energy
        Picasso.get().load(item.image).into(binding.imageView)
        viewModel.fetchRecipe(item.url).observe(viewLifecycleOwner, Observer {
            binding.ticketTextView.text = it.ingredients
            binding.detailTextView.text = it.detail
            binding.progressLayout.visibility = View.GONE
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = RecipeStepsAdapter(requireActivity())
        binding.recyclerView.adapter = adapter

        viewModel.fetchRecipeStep(item.url).observe(viewLifecycleOwner){
            adapter.setListData(it)
            Log.d("stepsAdapter", "$it")
            binding.progressBar.visibility = View.GONE
        }

        return binding.root
    }

}