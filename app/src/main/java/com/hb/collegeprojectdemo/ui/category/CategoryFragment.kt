package com.hb.collegeprojectdemo.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hb.collegeprojectdemo.R
import com.hb.collegeprojectdemo.adapter.CategoryAdapter
import com.hb.collegeprojectdemo.dailog.AddCategoryDialog
import com.hb.collegeprojectdemo.database.model.Category
import com.hb.collegeprojectdemo.databinding.FragmentGalleryBinding
import com.hb.collegeprojectdemo.utils.Preference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private lateinit var adapter: CategoryAdapter

    val categoryViewModel: CategoryViewModel by viewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var addCategoryDialog: AddCategoryDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initAdapter()
        initObserver()
        initListeners()
        categoryViewModel.addCategory()
        Preference.init(requireContext())

        categoryViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    private fun goToHome(){
        Preference.setUserLoggedIn(false)
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)

    }

    private fun initListeners() {
        binding.categoryAdd.setOnClickListener {
            addCategoryDialog = AddCategoryDialog(requireActivity()).apply {


                this.onTryAgainListener = object : AddCategoryDialog.OnTryAgainListener {
                    override fun okButtonClicked(category: Category) {

                        categoryViewModel.addCategoryFromUser(category)
                        addCategoryDialog.dismiss()

                    }

                    override fun cancelButtonClicked() {
                        addCategoryDialog.dismiss()
                    }

                }
            }
            addCategoryDialog.show()


        }

        binding.logOut.setOnClickListener {
            goToHome()
        }
    }

    private fun initObserver() {
        categoryViewModel.getAllCategoryState.observe(viewLifecycleOwner) {
            when (it) {
                is CategoryState.Success -> {
                    adapter.updateListLayout(it.categories)
                    binding.progressBarContainer.visibility = View.GONE
                    
                }

                is CategoryState.Error -> {
                    binding.progressBarContainer.visibility = View.GONE


                }
                is CategoryState.IsLoading -> {
                    binding.progressBarContainer.visibility = View.VISIBLE
                }
            }
        }

        categoryViewModel.addCategoryState.observe(viewLifecycleOwner) {
            when (it) {
                is CategoryState.Success -> {
                    binding.progressBarContainer.visibility = View.GONE

                    adapter.updateListLayout(it.categories)

                }

                is CategoryState.Error -> {
                    binding.progressBarContainer.visibility = View.GONE



                }
                is CategoryState.IsLoading -> {
                    binding.progressBarContainer.visibility = View.VISIBLE

                }
            }
        }


    }

    private fun initAdapter() {
        adapter = CategoryAdapter()
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.categoryRecyclerView.adapter = adapter
        adapter.checkClickListener = { category, position , delete->
            if(delete){
                categoryViewModel.deleteCategory(category)

            }else{
                val args = Bundle().apply {
                    putString("category_data", category.name)
                }
                args.putString("category_data_id","${category.id}")
                findNavController().navigate(R.id.action_homeFragment_to_SlideShowFragment, args)
            }


        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}