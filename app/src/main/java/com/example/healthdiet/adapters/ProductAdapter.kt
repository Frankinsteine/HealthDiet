package com.example.healthdiet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.healthdiet.BR
import com.example.healthdiet.classes.Product
import com.example.healthdiet.databinding.DietItemBinding
import com.example.healthdiet.databinding.ProductItemBinding
import com.example.healthdiet.fragments.chooseDietDirections
import com.example.healthdiet.models.DietButton

class ProductAdapter(private val context: Context): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var items = mutableListOf<Product>()
    fun setListData(data: MutableList<Product>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) = with(itemView) {
            binding.setVariable(BR.item, item)
            // apply changes
            binding.executePendingBindings()

            itemView.setOnClickListener{
                val action = chooseDietDirections.actionChooseDietToNavigationDashboard()
                findNavController().navigate(action)
                Toast.makeText(context, "Диета выбрана!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}