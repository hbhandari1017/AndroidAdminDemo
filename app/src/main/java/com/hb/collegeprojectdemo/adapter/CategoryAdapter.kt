package com.hb.collegeprojectdemo.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hb.collegeprojectdemo.database.model.Category
import com.hb.collegeprojectdemo.databinding.CategoryListViewBinding


class CategoryAdapter  : ListAdapter<Category, CategoryHorizontalListViewHolder>(DiffCallback()) {

    private lateinit var adapterBinding:CategoryListViewBinding

    var checkClickListener: ((Category, Int) -> Unit)? = null




    private class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHorizontalListViewHolder {
        adapterBinding = CategoryListViewBinding.inflate( LayoutInflater.from(parent.context),parent,false)

        return CategoryHorizontalListViewHolder(adapterBinding,checkClickListener)
    }

    override fun onBindViewHolder(holder: CategoryHorizontalListViewHolder, position: Int) {
        holder.bind(getItem(position),position)


    }

    fun updateListLayout(dataList: List<Category>) {
        submitList(dataList)
    }

}