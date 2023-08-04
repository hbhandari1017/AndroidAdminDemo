package com.hb.collegeprojectdemo.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hb.collegeprojectdemo.database.model.Category
import com.hb.collegeprojectdemo.databinding.CategoryListViewBinding


class CommonAdapter  : ListAdapter<Category, HorizontalListViewHolder>(DiffCallback()) {

    private lateinit var adapterBinding:CategoryListViewBinding



    private class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalListViewHolder {
        adapterBinding = CategoryListViewBinding.inflate( LayoutInflater.from(parent.context),parent,false)

        return HorizontalListViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: HorizontalListViewHolder, position: Int) {
        holder.bind(getItem(position))


    }

    fun updateListLayout(dataList: List<Category>) {
        submitList(dataList)
    }

}