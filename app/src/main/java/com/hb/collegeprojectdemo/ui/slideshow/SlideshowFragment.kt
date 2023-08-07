package com.hb.collegeprojectdemo.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hb.collegeprojectdemo.R
import com.hb.collegeprojectdemo.adapter.ProductAdapter
import com.hb.collegeprojectdemo.databinding.FragmentSlideshowBinding
import com.hb.collegeprojectdemo.utils.Preference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    private lateinit var adapter: ProductAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val slideshowViewModel: SlideshowViewModel by viewModels()



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

        slideshowViewModel.addProduct(id!!.toInt())
        initObservers()
        initListeners()
        initAdapter()
        return root
    }

    private fun goToHome(){
        Preference.setUserLoggedIn(false)
        findNavController().navigate(R.id.action_slideShow_to_HomeFragment)

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

        binding.logOut.setOnClickListener {
            goToHome()
        }
    }

    private fun initAdapter() {
        adapter = ProductAdapter()
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.categoryRecyclerView.adapter = adapter


    }

    private fun initObservers() {
    slideshowViewModel.addProductState.observe(viewLifecycleOwner){
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