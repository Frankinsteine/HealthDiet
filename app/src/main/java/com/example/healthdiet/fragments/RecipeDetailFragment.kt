package com.example.healthdiet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.healthdiet.databinding.FragmentEventDetailBinding
import com.example.healthdiet.viewmodels.RecipeViewModel
import com.squareup.picasso.Picasso

class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailBinding
    private val viewModel by lazy { ViewModelProvider(this).get(RecipeViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

        viewModel.fetchEvent(item.url).observe(viewLifecycleOwner, Observer {
            binding.ticketTextView.text = it.ingredients
            binding.detailTextView.text = it.detail
            binding.progressLayout.visibility = View.GONE
        })

        return binding.root
    }

}