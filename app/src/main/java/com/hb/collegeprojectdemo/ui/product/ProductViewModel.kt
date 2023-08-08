package com.hb.collegeprojectdemo.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.collegeprojectdemo.database.model.Product
import com.hb.collegeprojectdemo.repo.CommonRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject
sealed class ProductState {
    object IsLoading : ProductState()
    data class Success(val products: List<Product>) : ProductState()
    data class Error(val message: String?) : ProductState()
}
@ExperimentalCoroutinesApi
@HiltViewModel
class ProductViewModel   @Inject constructor(private val repo: CommonRepo) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text

    private var _addProductState: MutableLiveData<ProductState> = MutableLiveData()
    val addProductState: LiveData<ProductState> get() = _addProductState//After otp validation login state (success or failure)


    fun addProduct(id:Int) {
        _addProductState.postValue(ProductState.IsLoading)
        viewModelScope.launch {
            val productFromDb = repo.getAllProducts(id)
            if (productFromDb.isEmpty()) {

                val listOfCategory = ArrayList<Product>()
                val tempCategory = Product(name = "product1", price = "2", categoryId = id)
                val tempCategory2 = Product(name = "product2", price = "4", categoryId = id)
                listOfCategory.add(tempCategory)
                listOfCategory.add(tempCategory2)
                val allData = repo.addProduct(listOfCategory)
                if (allData.isEmpty()) {
                    _addProductState.postValue(ProductState.Error(message = "error"))
                } else {
                    _addProductState.postValue(ProductState.Success(products = repo.getAllProducts(id)))

                }

            }else{
                _addProductState.postValue(ProductState.Success(products = productFromDb))

            }
        }

    }


}