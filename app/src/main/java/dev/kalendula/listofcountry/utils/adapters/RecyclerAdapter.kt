package dev.kalendula.listofcountry.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.kalendula.listofcountry.data.database.CountryEntity
import dev.kalendula.listofcountry.databinding.ItemListBinding
import dev.kalendula.listofcountry.utils.IOnItemClick

class RecyclerAdapter(private val click: IOnItemClick): RecyclerView.Adapter<RecyclerAdapter.CountryViewHolder>() {

    private var items : List<CountryEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CountryViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        binding.onClickInterface = click
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(list : List<CountryEntity>){
        items = list
        notifyDataSetChanged()
    }

    class CountryViewHolder(private val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(c : CountryEntity, position: Int){
            binding.country = c
            binding.position = position
            binding.executePendingBindings()
        }
    }
}

