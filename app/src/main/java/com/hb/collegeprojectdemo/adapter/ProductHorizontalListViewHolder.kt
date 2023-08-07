package com.hb.collegeprojectdemo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hb.collegeprojectdemo.database.model.Product
import com.hb.collegeprojectdemo.databinding.CategoryListViewBinding


class ProductHorizontalListViewHolder(private val binding: CategoryListViewBinding) :
    RecyclerView.ViewHolder(binding.root) {



    fun bind(category: Product , position: Int) {
        binding.categoryName.text = category.name


    }





}