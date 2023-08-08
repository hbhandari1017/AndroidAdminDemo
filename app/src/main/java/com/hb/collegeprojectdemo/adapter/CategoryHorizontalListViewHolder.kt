package com.hb.collegeprojectdemo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hb.collegeprojectdemo.database.model.Category
import com.hb.collegeprojectdemo.databinding.CategoryListViewBinding


class CategoryHorizontalListViewHolder(private val binding: CategoryListViewBinding, var checkClickListener: ((Category, Int,Boolean) -> Unit)? = null) :
    RecyclerView.ViewHolder(binding.root) {



    fun bind(category: Category , position: Int) {
        binding.categoryName.text = category.name
        binding.categoryNameContainer.setOnClickListener {
            checkClickListener?.invoke(category,position,false)

        }
        binding.categoryNameContainer.setOnLongClickListener {
            checkClickListener?.invoke(category,position,true)

            true
        }

    }





}