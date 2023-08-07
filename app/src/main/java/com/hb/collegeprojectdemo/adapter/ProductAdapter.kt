package com.hb.collegeprojectdemo.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hb.collegeprojectdemo.database.model.Product
import com.hb.collegeprojectdemo.databinding.CategoryListViewBinding


class ProductAdapter  : ListAdapter<Product, ProductHorizontalListViewHolder>(DiffCallback()) {

    private lateinit var adapterBinding:CategoryListViewBinding





    private class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHorizontalListViewHolder {
        adapterBinding = CategoryListViewBinding.inflate( LayoutInflater.from(parent.context),parent,false)

        return ProductHorizontalListViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: ProductHorizontalListViewHolder, position: Int) {
        holder.bind(getItem(position),position)


    }

    fun updateListLayout(dataList: List<Product>) {
        submitList(dataList)
    }

}