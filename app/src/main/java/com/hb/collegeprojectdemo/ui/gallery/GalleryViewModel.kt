package com.hb.collegeprojectdemo.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.collegeprojectdemo.database.model.Category
import com.hb.collegeprojectdemo.database.model.User
import com.hb.collegeprojectdemo.repo.CommonRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CategoryState {
    object IsLoading : CategoryState()
    data class Success(val categories: List<Category>) : CategoryState()
    data class Error(val message: String?) : CategoryState()
}
class GalleryViewModel  @Inject constructor(private val repo: CommonRepo)  : ViewModel() {

    private var _getAllCategoryState: MutableLiveData<CategoryState> = MutableLiveData()
    val getAllCategoryState: LiveData<CategoryState> get() = _getAllCategoryState//After otp validation login state (success or failure)

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
    fun getAllCategory(user: User) {
        _getAllCategoryState.postValue(CategoryState.IsLoading)
        viewModelScope.launch {
            val allData = repo.getAllCategory()
            _getAllCategoryState.postValue(CategoryState.Success(categories = allData))



        }


    }



}