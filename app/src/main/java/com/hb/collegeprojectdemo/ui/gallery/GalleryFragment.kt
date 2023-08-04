package com.hb.collegeprojectdemo.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hb.collegeprojectdemo.adapter.CommonAdapter
import com.hb.collegeprojectdemo.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private lateinit var adapter: CommonAdapter

    val galleryViewModel: GalleryViewModel by viewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initAdapter()
        initObserver()

        galleryViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    private fun initObserver() {
        galleryViewModel.getAllCategoryState.observe(viewLifecycleOwner) {
            when (it) {
                is CategoryState.Success -> {
                    adapter.updateListLayout(it.categories)
                    
                }

                is CategoryState.Error -> {


                }
                is CategoryState.IsLoading -> {
                }
            }
        }

    }

    private fun initAdapter() {
        adapter = CommonAdapter()
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.categoryRecyclerView.adapter = adapter


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}