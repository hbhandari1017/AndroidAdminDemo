package com.hb.collegeprojectdemo.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.collegeprojectdemo.database.model.Category
import com.hb.collegeprojectdemo.database.model.User
import com.hb.collegeprojectdemo.repo.CommonRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CategoryState {
    object IsLoading : CategoryState()
    data class Success(val categories: List<Category>) : CategoryState()
    data class Error(val message: String?) : CategoryState()
}
@ExperimentalCoroutinesApi
@HiltViewModel
class GalleryViewModel @Inject constructor(private val repo: CommonRepo) : ViewModel() {

    private var _getAllCategoryState: MutableLiveData<CategoryState> = MutableLiveData()
    val getAllCategoryState: LiveData<CategoryState> get() = _getAllCategoryState

    private var _addCategoryState: MutableLiveData<CategoryState> = MutableLiveData()
    val addCategoryState: LiveData<CategoryState> get() = _addCategoryState


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

    fun addCategory() {
        _addCategoryState.postValue(CategoryState.IsLoading)
        viewModelScope.launch {
            val categoryFromDb = repo.getAllCategory()
            if (categoryFromDb.isEmpty()) {


                val listOfCategory = ArrayList<Category>()
                val tempCategory = Category(name = "category1")
                val tempCategory2 = Category(name = "category2")
                val tempCategory3 = Category(name = "category3")
                val tempCategory4 = Category(name = "category4")
                listOfCategory.add(tempCategory)
                listOfCategory.add(tempCategory2)
                listOfCategory.add(tempCategory3)
                listOfCategory.add(tempCategory4)
                val allData = repo.addCategory(listOfCategory)
                if (allData.isEmpty()) {
                    _getAllCategoryState.postValue(CategoryState.Error(message = "error"))
                } else {
                    _getAllCategoryState.postValue(CategoryState.Success(categories = repo.getAllCategory()))

                }

            }else{
                _getAllCategoryState.postValue(CategoryState.Success(categories = categoryFromDb))

            }
        }

    }

    fun addCategoryFromUser(category: Category) {
        _addCategoryState.postValue(CategoryState.IsLoading)
        viewModelScope.launch {
            val allData = repo.addCategoryFromUser(category)
            if (allData.toInt() == 0) {
                _getAllCategoryState.postValue(CategoryState.Error(message = "error"))
            } else {
                _getAllCategoryState.postValue(CategoryState.Success(categories = repo.getAllCategory()))

            }

        }


    }



}