package com.hb.collegeprojectdemo.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hb.collegeprojectdemo.adapter.ProductAdapter
import com.hb.collegeprojectdemo.databinding.FragmentSlideshowBinding
import com.hb.collegeprojectdemo.utils.Preference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    private lateinit var adapter: ProductAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val productViewModel: ProductViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val inputText = arguments?.getString("category_data")
        var id = arguments?.getString("category_data_id")
        Preference.init(requireContext())

        val textView: TextView = binding.homeTitle
        textView.text = inputText

        productViewModel.addProduct(id!!.toInt())
        initObservers()
        initListeners()
        initAdapter()
        return root
    }



    private fun initListeners() {
//        binding.categoryAdd.setOnClickListener {
//            addCategoryDialog = SavePhotoDialog(requireActivity()).apply {
//
//
//                this.onTryAgainListener = object : SavePhotoDialog.OnTryAgainListener {
//                    override fun okButtonClicked(category: Category) {
//
//                        galleryViewModel.addCategoryFromUser(category)
//                        addCategoryDialog.dismiss()
//
//                    }
//
//                    override fun cancelButtonClicked() {
//                        addCategoryDialog.dismiss()
//                    }
//
//                }
//            }
//            addCategoryDialog.show()
//
//
//        }


    }

    private fun initAdapter() {
        adapter = ProductAdapter()
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.categoryRecyclerView.adapter = adapter


    }

    private fun initObservers() {
    productViewModel.addProductState.observe(viewLifecycleOwner){
        when (it) {
            is ProductState.Success -> {
                adapter.updateListLayout(it.products)
                binding.progressBarContainer.visibility = View.GONE


            }

            is ProductState.Error -> {
                binding.progressBarContainer.visibility = View.GONE


            }
            is ProductState.IsLoading -> {
                binding.progressBarContainer.visibility = View.VISIBLE
            }
        }

    }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}