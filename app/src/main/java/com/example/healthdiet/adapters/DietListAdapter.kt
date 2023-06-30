package com.example.healthdiet.adapters

import android.content.Context
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.healthdiet.BR
import com.example.healthdiet.R
import com.example.healthdiet.databinding.DietItemBinding
import com.example.healthdiet.fragments.chooseDietDirections
import com.example.healthdiet.models.DietButton


class DietListAdapter(private val context: Context): RecyclerView.Adapter<DietListAdapter.ViewHolder>() {

    private var items = mutableListOf<DietButton>()
    fun setListData(data: MutableList<DietButton>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DietItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onBindViewHolder(holder: DietListAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: DietItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DietButton) = with(itemView) {
            binding.setVariable(BR.item, item)
            // apply changes
            binding.executePendingBindings()

            itemView.setOnClickListener{
                val action = chooseDietDirections.actionChooseDietToNavigationDashboard(9)
                findNavController().navigate(action)
                Toast.makeText(context, "Диета выбрана!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}