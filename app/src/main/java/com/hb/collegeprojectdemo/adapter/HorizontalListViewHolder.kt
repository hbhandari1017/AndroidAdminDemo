package com.hb.collegeprojectdemo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hb.collegeprojectdemo.database.model.Category
import com.hb.collegeprojectdemo.databinding.CategoryListViewBinding


class HorizontalListViewHolder(private val binding: CategoryListViewBinding,) :
    RecyclerView.ViewHolder(binding.root) {



    fun bind(category: Category) {
        binding.categoryName.text = category.name
    }





}