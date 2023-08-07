package com.pixend.app.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import com.pixend.app.databinding.PhotosaveDailogBinding


class SavePhotoDialog(context: Context) : Dialog(context) {
    private lateinit var binding:PhotosaveDailogBinding
    var message: String = "message"
    var onTryAgainListener: OnTryAgainListener? = null
    var showCancelButton=true
    var textforOkButton = ""

    init {
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = PhotosaveDailogBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.photosave_dailog)
        if(showCancelButton){
            binding.cancel.visibility= View.VISIBLE
        } else{
            binding.cancel.visibility=View.GONE
        }
        if(textforOkButton.isNotEmpty()){
            binding. okBtnforphoto.text = textforOkButton
        }
        binding. okBtnforphoto.setOnClickListener {
            onTryAgainListener?.okButtonClicked()

        }
        binding.cancel.setOnClickListener {
            onTryAgainListener?.cancelButtonClicked()

        }
        binding. dialogDescription.text = message

    }
    interface OnTryAgainListener {
        fun okButtonClicked()
        fun cancelButtonClicked()

    }


}


