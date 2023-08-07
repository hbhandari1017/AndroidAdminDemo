package com.hb.collegeprojectdemo.dailog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.hb.collegeprojectdemo.database.model.Category
import com.hb.collegeprojectdemo.databinding.PhotosaveDailogBinding


class SavePhotoDialog(context: Context) : Dialog(context) {
    private lateinit var binding: PhotosaveDailogBinding
    var onTryAgainListener: OnTryAgainListener? = null

    init {
        setCancelable(true)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = PhotosaveDailogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding. okBtnforphoto.setOnClickListener {
            val category = Category(name = binding.addCategoryEditText.text.toString())
            onTryAgainListener?.okButtonClicked(category)

        }
        binding.cancel.setOnClickListener {
            onTryAgainListener?.cancelButtonClicked()

        }

    }
    interface OnTryAgainListener {
        fun okButtonClicked(category: Category)
        fun cancelButtonClicked()

    }


}


