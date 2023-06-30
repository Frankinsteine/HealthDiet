package com.example.healthdiet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.healthdiet.BR
import com.example.healthdiet.databinding.RecipeItemBinding
import com.example.healthdiet.databinding.RecipeStepBinding
import com.example.healthdiet.fragments.HomeFragmentDirections
import com.example.healthdiet.models.RecipeItem
import com.example.healthdiet.models.RecipeStep
import com.squareup.picasso.Picasso

class RecipeStepsAdapter(private val context: Context): RecyclerView.Adapter<RecipeStepsAdapter.ViewHolder>() {

    private var items = mutableListOf<RecipeStep>()
    fun setListData(data: MutableList<RecipeStep>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeStepBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onBindViewHolder(holder: RecipeStepsAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: RecipeStepBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeStep) = with(itemView) {
            binding.setVariable(BR.item, item)
            // apply changes
            binding.executePendingBindings()
            // set image
            Picasso.get().load(item.image).into(binding.imageView)
        }

    }
}