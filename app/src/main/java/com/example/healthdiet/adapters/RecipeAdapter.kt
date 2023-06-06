package com.example.healthdiet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.healthdiet.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.healthdiet.databinding.RecipeItemBinding
import com.example.healthdiet.fragments.HomeFragmentDirections
import com.example.healthdiet.models.RecipeItem
import com.example.healthdiet.models.RecipeStep
import com.squareup.picasso.Picasso

class RecipeAdapter(private val context: Context): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private var items = mutableListOf<RecipeItem>()
    fun setListData(data: MutableList<RecipeItem>) {
        items = data
        notifyDataSetChanged()
    }
    private var steps = mutableListOf<RecipeStep>()
    fun setStepData(data: MutableList<RecipeStep>) {
        steps = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onBindViewHolder(holder: RecipeAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: RecipeItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeItem) = with(itemView) {
            binding.setVariable(BR.item, item)
            // apply changes
            binding.executePendingBindings()
            // set image
            Picasso.get().load(item.image).into(binding.imageView)

            itemView.setOnClickListener{
                val action = HomeFragmentDirections.actionNavigationHomeToEventDetailFragment(item)
                findNavController().navigate(action)
            }
        }

    }

}